package org.example.atp_project.controller.torneo;

import jakarta.validation.Valid;
import org.example.atp_project.dto.torneo.CreateTorneoDto;
import org.example.atp_project.dto.torneo.GetTorneoDto;
import org.example.atp_project.dto.torneo.UpdateTorneoDto;
import org.example.atp_project.models.Torneo;
import org.example.atp_project.service.torneo.TorneoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/torneo")

public class TorneoRestController {

    private Logger log = LoggerFactory.getLogger(TorneoRestController.class);

    private final TorneoServicio torneoServicio;

    @Autowired
    public TorneoRestController(TorneoServicio torneoServicio) {
        this.torneoServicio = torneoServicio;
    }

    @GetMapping()
    public ResponseEntity<Page<GetTorneoDto>> getTorneoAll(
            @RequestParam(required = false) Optional<String> nombre,
            @RequestParam(required = false) Optional<String> ubicacion,
            @RequestParam(required = false) Optional<Torneo.TipoTorneo> tipoTorneo,
            @RequestParam(required = false) Optional<Torneo.Categoria> categoria,
            @RequestParam(required = false) Optional<Torneo.Superficie> superficie,
            @RequestParam(required = false) Optional<LocalDate> fecha_ini,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortP,
            @RequestParam(defaultValue = "asc") String direccion
    ) {
        log.info("Buscando todos los torneos.");

        Sort sort= direccion.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortP).ascending() : Sort.by(sortP).descending();

        return ResponseEntity.ok(torneoServicio.findAll(nombre, ubicacion, tipoTorneo, categoria, superficie,
                fecha_ini, PageRequest.of(page, size, sort)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<GetTorneoDto> getTorneoById(@PathVariable Long id) {
        log.info("Buscando torneo con id:" + id);
        return ResponseEntity.ok(torneoServicio.findById(id));
    }


    @PostMapping()
    //@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_TORNEO')")
    public ResponseEntity<GetTorneoDto> createTorneo(@Valid @RequestBody CreateTorneoDto createTorneoDto) {
        log.info("Creando un nuevo torneo.");
        return ResponseEntity.status(HttpStatus.CREATED).body(torneoServicio.save(createTorneoDto));
    }

    @PostMapping("{idTorneo}/inscripcion/{idTenista}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_TORNEO')")
    public ResponseEntity<Void> inscripcionTorneo(@PathVariable Long idTorneo, @PathVariable Long idTenista) {
        log.info("Inscribiendo al tenista con id: " + idTenista + " al torneo con id: " + idTorneo);

        torneoServicio.entradasTorneo(idTenista, idTorneo);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(value = "/imagen/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_TORNEO')")
    public ResponseEntity<GetTorneoDto> imagenTorneo(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        log.info("Actualizando imagen del torneo con id: " + id);

        if (!file.isEmpty()) {
            return ResponseEntity.ok(torneoServicio.updateImagen(id, file));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se envio esta " +
                    "imagen para el torneo.");
        }

    }



    @PutMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_TORNEO')")
    public ResponseEntity<GetTorneoDto> updateTorneo(@PathVariable Long id, @Valid @RequestBody UpdateTorneoDto updateTorneoDto) {
        log.info("Actualizando torneo con id: " + id);
        return ResponseEntity.ok(torneoServicio.update(id, updateTorneoDto));
    }


    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_TORNEO')")
    public ResponseEntity<Void> deleteTorneo(@PathVariable Long id) {
        log.info("Eliminando torneo con id: " + id);
        torneoServicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
