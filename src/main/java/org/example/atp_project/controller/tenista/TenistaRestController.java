package org.example.atp_project.controller.tenista;

import jakarta.validation.Valid;
import org.example.atp_project.dto.tenista.CreateTenistaDto;
import org.example.atp_project.dto.tenista.GetTenistaDto;
import org.example.atp_project.dto.tenista.UpdateTenistaDto;
import org.example.atp_project.models.Tenista;
import org.example.atp_project.service.tenista.TenistaServicio;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tenista")
public class TenistaRestController {

    private final TenistaServicio tenistaServicio;

    private final Logger log = LoggerFactory.getLogger(TenistaRestController.class);

    @Autowired
    public TenistaRestController(TenistaServicio tenistaServicio) {
        this.tenistaServicio = tenistaServicio;
    }

    @GetMapping()
    public ResponseEntity<Page<GetTenistaDto>> getTenistaAll(
            @RequestParam(required = false) Optional<Integer> ranking,
            @RequestParam(required = false) Optional<String> nombreCompleto,
            @RequestParam(required = false) Optional<String> pais,
            @RequestParam(required = false) Optional<String> entrenador,
            @RequestParam(required = false) Optional<Integer> puntos,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ranking") String sortP,
            @RequestParam(defaultValue = "asc") String direccion
    ) {
        log.info("Buscando todos los tenistas.");

        Sort sort= direccion.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortP).ascending() : Sort.by(sortP).descending();

        return ResponseEntity.ok(tenistaServicio.findAll(ranking, nombreCompleto, pais,
                entrenador, puntos, PageRequest.of(page, size, sort)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTenistaDto> getTenistaById(@PathVariable Long id) {
        log.info("Buscando tenista con id:" + id);
        return ResponseEntity.ok(tenistaServicio.findById(id));
    }


    @PostMapping()
    //@PreAuthorize("hasAnyRole('ADMIN','ADMIN_TENISTA')")
    public ResponseEntity<GetTenistaDto> createTenista(@Valid @RequestBody CreateTenistaDto createTenistaDto) {
        log.info("Creando un nuevo tenista.");
        return ResponseEntity.status(HttpStatus.CREATED).body(tenistaServicio.save(createTenistaDto));
    }

    @PatchMapping(value = "/imagen/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@PreAuthorize("hasAnyRole('ADMIN','ADMIN_TENISTA')")
    public ResponseEntity<GetTenistaDto> imagenTenista(
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file
            ){
        log.info("Actualizando imagen de tenista con id: " + id);

        if(!file.isEmpty()) {
            return ResponseEntity.ok(tenistaServicio.updateImagen(id, file));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se envio esta " +
                    "imagen para el tenista.");
        }

    }


    @PutMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN','ADMIN_TENISTA')")
    public ResponseEntity<GetTenistaDto> updateTenista(@PathVariable Long id,
                                                       @Valid @RequestBody UpdateTenistaDto updateTenistaDto) {
        log.info("Actualizando tenista con id: " + id);
        return ResponseEntity.ok(tenistaServicio.update(id, updateTenistaDto));
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN','ADMIN_TENISTA')")
    public ResponseEntity<Void> deleteTenista(@PathVariable Long id) {
        log.info("Eliminando tenista con id: " + id);

        tenistaServicio.deleteById(id);

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
