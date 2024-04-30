package org.example.atp_project.service.torneo;

import org.example.atp_project.almacenamiento.StorageService;
import org.example.atp_project.dto.torneo.CreateTorneoDto;
import org.example.atp_project.dto.torneo.GetTorneoDto;
import org.example.atp_project.dto.torneo.UpdateTorneoDto;
import org.example.atp_project.excepciones.tenista.TenistaNoEncontrado;
import org.example.atp_project.excepciones.torneo.ListaLlenaException;
import org.example.atp_project.excepciones.torneo.TenistaYaApuntado;
import org.example.atp_project.excepciones.torneo.TorneoNoEncontrado;
import org.example.atp_project.mappers.TorneoMapper;
import org.example.atp_project.models.Tenista;
import org.example.atp_project.models.Torneo;
import org.example.atp_project.repos.TenistaRepo;
import org.example.atp_project.repos.TorneoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TorneoServicioImpl implements TorneoServicio {

    private final Logger log = LoggerFactory.getLogger(TorneoServicioImpl.class);
    private final TorneoRepo torneoRepo;

    private final TenistaRepo tenistaRepo;

    private final StorageService storageService;

    private final TorneoMapper torneoMapper;

    @Autowired
    public TorneoServicioImpl(TorneoRepo torneoRepo, TenistaRepo tenistaRepo, StorageService storageService, TorneoMapper torneoMapper) {
        this.torneoRepo = torneoRepo;
        this.tenistaRepo = tenistaRepo;
        this.storageService = storageService;
        this.torneoMapper = torneoMapper;
    }


    @Override
    public Page<GetTorneoDto> findAll(Optional<String> nombre, Optional<String> ubicacion,
                                      Optional<Torneo.TipoTorneo> tipoTorneo, Optional<Torneo.Categoria> categoria,
                                      Optional<Torneo.Superficie> superficie, Optional<LocalDate> fecha_ini,
                                      Pageable pageable) {

        log.info("Buscando todos los torneos.");

        Specification<Torneo> specNombre = (root, query, criteriaBuilder) ->
                nombre.map(n -> criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")),
                        "%" + n.toLowerCase() + "%"))
                       .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<Torneo> specUbicacion = (root, query, criteriaBuilder) ->
                ubicacion.map(u -> criteriaBuilder.like(criteriaBuilder.lower(root.get("ubicacion")),
                                "%" + u.toLowerCase() + "%"))
                        .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<Torneo> specTorneo = (root, query, criteriaBuilder) ->
                tipoTorneo.map(t -> criteriaBuilder.equal(root.get("tipoTorneo"), t))
                       .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<Torneo> specCategoria = (root, query, criteriaBuilder) ->
                categoria.map(c -> criteriaBuilder.equal(root.get("categoria"), c))
                       .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<Torneo> specSuperficie = (root, query, criteriaBuilder) ->
                superficie.map(s -> criteriaBuilder.equal(root.get("superficie"), s))
                       .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<Torneo> specFecha_ini = (root, query, criteriaBuilder) ->
                fecha_ini.map(f -> criteriaBuilder.equal(root.get("fecha_ini"), f))
                       .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));


        Specification<Torneo> busquedaCriterio = Specification.where(specNombre)
                .and(specUbicacion)
                .and(specTorneo)
                .and(specCategoria)
                .and(specSuperficie)
                .and(specFecha_ini);

        return torneoRepo.findAll(busquedaCriterio, pageable).map(torneoMapper::torneoToGetDto);
    }

    @Override
    public GetTorneoDto findById(Long id) {
        log.info("Buscando torneo con id: " + id);

        return torneoMapper.torneoToGetDto(torneoRepo.findById(id)
                .orElseThrow(() -> new TorneoNoEncontrado(id)));
    }

    @Override
    public GetTorneoDto save(CreateTorneoDto createTorneoDto) {
        log.info("Guardando torneo: " + createTorneoDto);

        var torneo = torneoRepo.save(torneoMapper.createDtoToTorneo(createTorneoDto));


        return torneoMapper.torneoToGetDto(torneo);
    }

    @Override
    public GetTorneoDto update(Long id, UpdateTorneoDto updateTorneoDto) {
        log.info("Actualizando torneo con el id: " + id);

        var torneoBuscado = torneoRepo.findById(id)
                .orElseThrow(() -> new TorneoNoEncontrado(id));

        var torneoActualizado = torneoRepo.save(torneoMapper.updateDtoToTorneo(updateTorneoDto, torneoBuscado));

        return torneoMapper.torneoToGetDto(torneoActualizado);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminando torneo con el id: " + id);

        var torneo = this.findById(id);

        torneoRepo.deleteById(id);

        if (torneo.getImagen() != null && !torneo.getImagen().equals(Torneo.IMAGE_DEFAULT)) {
            storageService.delete(torneo.getImagen());
        }
    }

    @Override
    public GetTorneoDto updateImagen(Long id, MultipartFile imagen) {
        log.info("Actualizando imagen del torneo con el id: " + id);

        var torneoBuscado = torneoRepo.findById(id)
                .orElseThrow(() -> new TorneoNoEncontrado(id));

        if (torneoBuscado.getImagen() != null) {
            storageService.delete(torneoBuscado.getImagen());
        }

        String imagenGuardada = storageService.store(imagen);
        String imagenUrl = storageService.getUrl(imagenGuardada);

        var torneoActualizado = new Torneo(
              torneoBuscado.getId(),
              torneoBuscado.getNombre(),
              torneoBuscado.getUbicacion(),
              torneoBuscado.getTipoTorneo(),
              torneoBuscado.getCategoria(),
              torneoBuscado.getSuperficie(),
              torneoBuscado.getEntradas(),
              torneoBuscado.getFecha_ini(),
              torneoBuscado.getFecha_fin(),
              torneoBuscado.getPremio(),
              imagenUrl,
              torneoBuscado.getTenistas()
        );

        var torneoActualizadoSave = torneoRepo.save(torneoActualizado);

        return torneoMapper.torneoToGetDto(torneoActualizadoSave);
    }


    public void entradasTorneo (Long idTenista, Long idTorneo) {
        Tenista tenistaExistente = tenistaRepo.findById(idTenista).orElse(null);
        Torneo torneoExistente = torneoRepo.findById(idTorneo).orElse(null);

        if (tenistaExistente != null){

            if (torneoExistente!= null) {

                if (torneoExistente.getTenistas().contains(tenistaExistente)){
                    throw new TenistaYaApuntado("Este tenista ("+ tenistaExistente.getNombreCompleto() +
                            ") ya esta apuntado al correspondiente torneo (" + torneoExistente.getNombre() + ")");
                }


                if (torneoExistente.getEntradas() != 0) {
                    torneoExistente.getTenistas().add(tenistaExistente);
                    torneoExistente.setEntradas(torneoExistente.getEntradas() - 1);
                    tenistaExistente.setTorneo(torneoExistente);
                    torneoRepo.save(torneoExistente);
                } else {
                    throw new ListaLlenaException("No quedan vacantes en este torneo: "+
                            torneoExistente.getNombre());
                }



            } else {
                throw new TorneoNoEncontrado(idTorneo);
            }

        } else {
            throw new TenistaNoEncontrado(idTenista);
        }

    }

}
