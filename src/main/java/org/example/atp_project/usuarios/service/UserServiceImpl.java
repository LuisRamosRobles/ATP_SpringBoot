package org.example.atp_project.usuarios.service;

import org.example.atp_project.usuarios.dto.GetUser;
import org.example.atp_project.usuarios.dto.RequestUser;
import org.example.atp_project.usuarios.excepciones.UserNoEncontrado;
import org.example.atp_project.usuarios.excepciones.UsernameEmailExiste;
import org.example.atp_project.usuarios.mappers.UserMapper;
import org.example.atp_project.usuarios.models.User;
import org.example.atp_project.usuarios.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                // Elegimos el métdo que queramos para buscar el usuario
                return userRepo.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));
            }
        };
    }

    @Override
    public Page<GetUser> findAll(Optional<String> username, Optional<String> email, Pageable pageable) {
        log.info("Buscando todos los usuarios.");

        Specification<User> specUsernameUser = (root, query, criteriaBuilder) ->
                username.map(m -> criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), "%" + m.toLowerCase() + "%"))
                        .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<User> specEmail = (root, query, criteriaBuilder) ->
                email.map(m -> criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + m.toLowerCase() + "%"))
                        .orElseGet(() -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

        Specification<User> criterio = Specification.where(specUsernameUser)
                .and(specEmail);

        return userRepo.findAll(criterio, pageable).map(userMapper::userToGetDto);
    }

    @Override
    public GetUser findById(Long id) {
        log.info("Buscando usuario con id: " + id);

        var user = userRepo.findById(id)
                .orElseThrow(() -> new UserNoEncontrado(id));

        return userMapper.userToGetDto(user);
    }

    @Override
    public GetUser save(RequestUser requestUser) {
        log.info("Creando un nuevo usuario: " + requestUser);

        userRepo.findByUsernameOrEmail(requestUser.getUsername(), requestUser.getEmail())
                .ifPresent(ex -> {
                    throw new UsernameEmailExiste("El username o email que has introducido " +
                            "ya esta en uso, por favor prueba uno diferente.");
                });

        return userMapper.userToGetDto(userRepo.save(userMapper.userCreate(requestUser)));
    }

    @Override
    public GetUser update(Long id, RequestUser requestUser) {
        log.info("Actualizando usuario con el id: " + id);

        userRepo.findById(id).orElseThrow(() -> new UserNoEncontrado(id));

        userRepo.findByUsernameOrEmail(requestUser.getUsername(), requestUser.getEmail())
                .ifPresent(ex -> {
                    if (!ex.getId().equals(id)) {
                        throw new UsernameEmailExiste("El username o email que has introducido " +
                                "ya esta en uso, por favor prueba uno diferente.");
                    }
                });

        return userMapper.userToGetDto(userRepo.save(userMapper.userUpdate(requestUser, id)));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminando usuario con el id: " + id);

        var user = userRepo.findById(id).orElseThrow(() -> new UserNoEncontrado(id));

        userRepo.delete(user);

    }
}
