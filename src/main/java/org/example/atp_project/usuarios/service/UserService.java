package org.example.atp_project.usuarios.service;

import org.example.atp_project.usuarios.dto.GetUser;
import org.example.atp_project.usuarios.dto.RequestUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {

    UserDetailsService userDetailsService();

    Page<GetUser> findAll(Optional<String> username, Optional<String> email,
                          Pageable pageable);

    GetUser findById(Long id);

    GetUser save(RequestUser requestUser);

    GetUser update(Long id, RequestUser requestUser);

    void deleteById(Long id);
}
