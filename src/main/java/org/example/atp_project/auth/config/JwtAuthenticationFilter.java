package org.example.atp_project.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.example.atp_project.auth.services.jwt.JwtService;
import org.example.atp_project.auth.services.users.AuthUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;

    private final AuthUsersService authUsersService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, AuthUsersService authUsersService) {
        this.jwtService = jwtService;
        this.authUsersService = authUsersService;
    }


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Iniciando filtro de autenticación.");
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        UserDetails userDetails;
        String userName;

        if (!StringUtils.hasText(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
            log.info("No se ha encontrado cabecera de autenticación.");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Cabecera de autenticación encontrada, procesando ...");
        jwt = authHeader.substring(7);

        try {
            log.info("Extrayendo nombre de usuario.");
            userName = jwtService.extractUserName(jwt);
            log.info("Nombre de usuario encontrado: " + userName);
        } catch (Exception e) {
            log.info("Token no válido");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token no autorizado o no válido");
            return;
        }

        log.info("Usuario autenticado.");
        if (StringUtils.hasText(userName)
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            log.info("Comprobando usuario y token.");
            try {
                log.info("Cargando detalles del usuario.");
                userDetails = authUsersService.loadUserByUsername(userName);
                log.info("Detalles del usuario cargados." + userDetails.toString());
            } catch (Exception e) {
                log.info("Usuario no encontrado.");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autorizado.");
                return;
            }

            log.info("Usuario encontrado.");
            if (jwtService.isTokenValid(jwt, userDetails)) {
                log.info("JWT válido");

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }

        filterChain.doFilter(request, response);

    }
}
