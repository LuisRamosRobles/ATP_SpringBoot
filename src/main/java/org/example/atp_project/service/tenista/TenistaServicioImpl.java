package org.example.atp_project.service.tenista;

import org.example.atp_project.almacenamiento.StorageService;
import org.example.atp_project.dto.tenista.CreateTenistaDto;
import org.example.atp_project.dto.tenista.GetTenistaDto;
import org.example.atp_project.dto.tenista.UpdateTenistaDto;
import org.example.atp_project.excepciones.tenista.TenistaNoEncontrado;
import org.example.atp_project.mappers.TenistaMapper;
import org.example.atp_project.models.Tenista;
import org.example.atp_project.repos.TenistaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TenistaServicioImpl implements TenistaServicio{

    private final Logger log = LoggerFactory.getLogger(TenistaServicioImpl.class);
    private final TenistaRepo tenistaRepo;

    private final StorageService storageService;

    private final TenistaMapper tenistaMapper;



    @Autowired
    public TenistaServicioImpl(TenistaRepo tenistaRepo, StorageService storageService, TenistaMapper tenistaMapper) {
        this.tenistaRepo = tenistaRepo;
        this.storageService = storageService;
        this.tenistaMapper = tenistaMapper;
    }


    @Override
    public Page<GetTenistaDto> findAll(Optional<Integer> ranking, Optional<String> nombreCompleto,
                                       Optional<String> pais, Optional<String> entrenador,
                                       Optional<Integer> puntos, Pageable pageable) {

        log.info("Buscando todos los tenistas.");

        Specification<Tenista> specRanking = (root, query, criteriaBuilder) ->
                ranking.map(r -> criteriaBuilder.equal(root.get("ranking"), r))
                        .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<Tenista> specNombreCompleto = (root, query, criteriaBuilder) ->
                nombreCompleto.map(n -> criteriaBuilder.like(criteriaBuilder.lower(root.get("nombreCompleto")),
                        "%" + n.toLowerCase() + "%"))
                        .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<Tenista> specPais = (root, query, criteriaBuilder) ->
                pais.map(p -> criteriaBuilder.like(criteriaBuilder.lower(root.get("pais")),
                        "%" + p.toLowerCase() + "%"))
                        .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<Tenista> specEntrenador = (root, query, criteriaBuilder) ->
                entrenador.map(e -> criteriaBuilder.like(criteriaBuilder.lower(root.get("entrenador")),
                        "%" + e.toLowerCase() + "%"))
                        .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<Tenista> specPuntos = (root, query, criteriaBuilder) ->
                puntos.map(p -> criteriaBuilder.equal(root.get("puntos"), p))
                        .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<Tenista> busquedaCriterio = Specification.where(specRanking)
                .and(specNombreCompleto)
                .and(specPais)
                .and(specEntrenador)
                .and(specPuntos);

        calcularRanking();

        calcularWinrate();

        return tenistaRepo.findAll(busquedaCriterio, pageable).map(tenistaMapper::tenistaToGetDto);
    }

    @Override
    public GetTenistaDto findById(Long id) {
        log.info("Buscando tenista con id: " + id);

        calcularRanking();

        calcularWinrate();

        return tenistaMapper.tenistaToGetDto(tenistaRepo.findById(id)
                .orElseThrow(() -> new TenistaNoEncontrado(id)));
    }

    @Override
    public GetTenistaDto save(CreateTenistaDto createTenistaDto) {
        log.info("Guardando tenista: " + createTenistaDto);
        var tenista = tenistaRepo.save(tenistaMapper.createDtoToTenista(createTenistaDto));

        calcularWinrate();

        calcularRanking();

        return tenistaMapper.tenistaToGetDto(tenista);
    }

    @Override
    public GetTenistaDto update(Long id, UpdateTenistaDto updateTenistaDto) {
        log.info("Actualizando tenista con el id: " + id);

        var tenistaBuscado = tenistaRepo.findById(id)
                .orElseThrow(() -> new TenistaNoEncontrado(id));

        var tenistaActualizado = tenistaRepo.save(tenistaMapper.updateDtoToTenista(updateTenistaDto, tenistaBuscado));

        calcularWinrate();
        calcularRanking();

        return tenistaMapper.tenistaToGetDto(tenistaActualizado);

    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminando tenista con el id: " + id);

        var tenista = this.findById(id);

        tenistaRepo.deleteById(id);

        if (tenista.getImagen() != null && !tenista.getImagen().equals(Tenista.IMAGE_DEFAULT)) {
            storageService.delete(tenista.getImagen());
        }

    }


    public GetTenistaDto updateImagen(Long id, MultipartFile imagen) {
        log.info("Actualizando imagen del tenista con el id: " + id);

        var tenistaBuscado = tenistaRepo.findById(id)
                .orElseThrow(() -> new TenistaNoEncontrado(id));

        if (tenistaBuscado.getImagen() != null) {
            storageService.delete(tenistaBuscado.getImagen());
        }

        String imagenGuardada = storageService.store(imagen);
        String imagenUrl = storageService.getUrl(imagenGuardada);

        var tenistaActualizado = new Tenista(
                tenistaBuscado.getId(),
                tenistaBuscado.getRanking(),
                tenistaBuscado.getNombreCompleto(),
                tenistaBuscado.getPais(),
                tenistaBuscado.getFechaNac(),
                tenistaBuscado.getEdad(),
                tenistaBuscado.getAltura(),
                tenistaBuscado.getPeso(),
                tenistaBuscado.getProfesionalDesde(),
                tenistaBuscado.getManoPref(),
                tenistaBuscado.getReves(),
                tenistaBuscado.getEntrenador(),
                tenistaBuscado.getPriceMoney(),
                tenistaBuscado.getBestRanking(),
                tenistaBuscado.getWin(),
                tenistaBuscado.getLost(),
                tenistaBuscado.getWin_rate(),
                imagenUrl,
                tenistaBuscado.getPuntos(),
                tenistaBuscado.getTorneo()
        );

        var tenistaActualizadoSave = tenistaRepo.save(tenistaActualizado);

        return tenistaMapper.tenistaToGetDto(tenistaActualizadoSave);

    }




    public void calcularWinrate() {
        List<Tenista> tenistaList = tenistaRepo.findAll();
        DecimalFormat decimal = new DecimalFormat("0.00");

        for (Tenista tenista : tenistaList) {
            int win = tenista.getWin();
            int lost = tenista.getLost();
            int total = win + lost;

            double winrate = ((double) win / total) * 100;

            tenista.setWin_rate(decimal.format(winrate) + "%");

            tenistaRepo.save(tenista);

        }
    }

    public void calcularRanking() {
        List<Tenista> tenistaList = tenistaRepo.findAll();

        List<Tenista> tenistaOrderList = tenistaList.stream()
                .sorted(Comparator.comparingInt(Tenista::getPuntos).reversed())
                .collect(Collectors.toList());


        for (int i = 0; i < tenistaOrderList.size(); i++) {
            Tenista tenista = tenistaOrderList.get(i);
            tenista.setRanking(i + 1);

            if (tenista.getBestRanking() == null || tenista.getRanking() < tenista.getBestRanking()){
                tenista.setBestRanking(tenista.getRanking());
            }

            tenistaRepo.save(tenista);


        }
    }

}
