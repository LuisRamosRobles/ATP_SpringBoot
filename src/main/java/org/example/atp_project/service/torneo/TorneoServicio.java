package org.example.atp_project.service.torneo;

import org.example.atp_project.dto.torneo.CreateTorneoDto;
import org.example.atp_project.dto.torneo.GetTorneoDto;
import org.example.atp_project.dto.torneo.UpdateTorneoDto;
import org.example.atp_project.models.Torneo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

public interface TorneoServicio {

    Page<GetTorneoDto> findAll (Optional<String> nombre, Optional<String> ubicacion,
                                Optional<Torneo.TipoTorneo> tipoTorneo, Optional<Torneo.Categoria> categoria,
                                Optional<Torneo.Superficie> superficie, Optional<LocalDate> fecha_ini,
                                Pageable pageable);

    GetTorneoDto findById(Long id);

    GetTorneoDto save(CreateTorneoDto createTorneoDto);

    void entradasTorneo(Long idTenista, Long idTorneo);

    GetTorneoDto update(Long id, UpdateTorneoDto updateTorneoDto);

    void deleteById(Long id);

    GetTorneoDto updateImagen(Long id, MultipartFile imagen);
}
