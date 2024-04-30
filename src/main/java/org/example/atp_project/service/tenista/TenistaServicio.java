package org.example.atp_project.service.tenista;

import org.example.atp_project.dto.tenista.CreateTenistaDto;
import org.example.atp_project.dto.tenista.GetTenistaDto;
import org.example.atp_project.dto.tenista.UpdateTenistaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface TenistaServicio {

    Page<GetTenistaDto> findAll(Optional<Integer> ranking, Optional<String> nombreCompleto,
                                Optional<String> pais, Optional<String> entrenador, Optional<Integer> puntos,
                                Pageable pageable);

    GetTenistaDto findById(Long id);

    GetTenistaDto save(CreateTenistaDto createTenistaDto);

    GetTenistaDto update(Long id, UpdateTenistaDto updateTenistaDto);

    void deleteById(Long id);

    GetTenistaDto updateImagen(Long id, MultipartFile imagen);


}
