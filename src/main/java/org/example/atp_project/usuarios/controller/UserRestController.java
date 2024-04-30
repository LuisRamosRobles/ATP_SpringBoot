package org.example.atp_project.usuarios.controller;

import org.example.atp_project.usuarios.dto.GetUser;
import org.example.atp_project.usuarios.dto.RequestUser;
import org.example.atp_project.usuarios.models.User;
import org.example.atp_project.usuarios.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@PreAuthorize("hasRole('USER')")
public class UserRestController {

    private final Logger log = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;


    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<GetUser>> getUsersAll(
            @RequestParam(required = false) Optional<String> username,
            @RequestParam(required = false) Optional<String> email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortP,
            @RequestParam(defaultValue = "asc") String direccion
            ) {
        log.info("Buscando todos los usuarios.");

        Sort sort = direccion.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortP).ascending() : Sort.by(sortP).descending();

        return ResponseEntity.ok(userService.findAll(username, email, PageRequest.of(page, size, sort)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GetUser> getUserById(@PathVariable Long id) {
        log.info("Buscando usuario con id:" + id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GetUser> createUser(@RequestBody RequestUser requestUser) {
        log.info("Creando un nuevo usuario.");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(requestUser));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GetUser> updateUser(@PathVariable Long id, @RequestBody RequestUser requestUser) {
        log.info("Actualizando usuario con id:" + id);
        return ResponseEntity.ok(userService.update(id, requestUser));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Eliminando usuario con id:" + id);
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/yo/perfil")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GetUser> getMyProfile(User user) {
        log.info("Buscando mi perfil.");
        return ResponseEntity.ok(userService.findById(user.getId()));
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
