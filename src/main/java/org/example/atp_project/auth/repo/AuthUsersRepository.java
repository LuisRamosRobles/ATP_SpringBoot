package org.example.atp_project.auth.repo;

import org.example.atp_project.usuarios.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
