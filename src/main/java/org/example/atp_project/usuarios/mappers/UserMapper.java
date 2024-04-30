package org.example.atp_project.usuarios.mappers;

import org.example.atp_project.usuarios.dto.GetUser;
import org.example.atp_project.usuarios.dto.RequestUser;
import org.example.atp_project.usuarios.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public GetUser userToGetDto(User user) {
        GetUser dto = new GetUser();
        dto.setId(user.getId());
        dto.setNombre(user.getNombre());
        dto.setApellido(user.getApellido());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());

        return dto;
    }

    public User userCreate(RequestUser requestUser) {
        User user = new User();
        user.setNombre(requestUser.getNombre());
        user.setApellido(requestUser.getApellido());
        user.setUsername(requestUser.getUsername());
        user.setEmail(requestUser.getEmail());
        user.setPassword(requestUser.getPassword());
        user.setRoles(requestUser.getRoles());

        return user;
    }

    public User userUpdate(RequestUser requestUser, Long id) {
        User user = new User();
        user.setId(id);
        user.setNombre(requestUser.getNombre());
        user.setApellido(requestUser.getApellido());
        user.setUsername(requestUser.getUsername());
        user.setEmail(requestUser.getEmail());
        user.setPassword(requestUser.getPassword());
        user.setRoles(requestUser.getRoles());

        return user;
    }
}
