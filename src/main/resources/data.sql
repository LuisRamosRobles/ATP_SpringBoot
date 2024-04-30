INSERT INTO tenista (nombre_completo,
                     pais,
                     fecha_nac,
                     altura, peso,
                     profesional_desde, mano_pref,
                     reves, entrenador, price_money,
                     win, lost, imagen, puntos)
VALUES ('Luis','España',
        '2002-07-08',
        1.80, 75,
        '2013-04-15', 0, 1,
        'Fray Luis de Leon',
        10578.45, 7, 1,
        'https://via.placeholder.com/150',
        278
        );

INSERT INTO tenista (nombre_completo,
                     pais,
                     fecha_nac,
                     altura, peso,
                     profesional_desde, mano_pref,
                     reves, entrenador, price_money,
                     win, lost, imagen, puntos)
VALUES ('Rodolfo','España',
        '1998-11-15',
        1.80, 75,
        '2013-04-15', 0, 1,
        'Rajoy',
        555.75, 9, 2,
        'https://via.placeholder.com/150',
        450
       );


INSERT INTO torneo (nombre, ubicacion,
                    tipo_torneo, categoria,
                    superficie, entradas, fecha_ini,
                    fecha_fin, premio, imagen)
VALUES ('Australia Masters 1000', 'Australia',
        2, 0, 1, 2, '2024-04-25',
        '2024-04-30', 1000000.00,
        'https://via.placeholder.com/150'
        );


INSERT INTO usuarios (nombre, apellido,
                      username, email,password)
VALUES ('Admin', 'Admin',
        'admin', 'admin@admin.es','$2a$10$a.z2EuNkzJnDT3Iy22gnz.0NdydaWdp3L27vN4NK1q.OmsuMC3FZ.'
        ); -- contraseña: admin1234

INSERT INTO usuarios (nombre, apellido,
                      username, email, password)
VALUES ('User', 'User',
        'user', 'user@user.es','$2a$10$93CHouIwPSB05w5BosOg2uvU8f60OaxDX9loVgZMHO8r2/rq781Fm'
       ); -- contraseña: user1234


INSERT INTO usuarios (nombre, apellido,
                      username, email, password)
VALUES ('Admin', 'Tenista',
        'tenistaAdmin', 'tenista@tenista.es','$2a$10$Vk3.7Vf.nWQwDCuWHkRZlOhvOgyb8oqW.jZCa.oXn7dIGbfssBLYK'
        ); -- contraseña: tenista1234

INSERT INTO usuarios (nombre, apellido,
                      username, email, password)
VALUES ('Admin', 'Torneo',
        'torneoAdmin', 'torneo@torneo.es','$2a$10$ezW.8C2TmVQ9beqrZkLwne9HABOx73nbyiJoZBNZChvsndjsR3CkO'
        ); -- contraseña: torneo1234

INSERT INTO user_roles (user_id, roles)
VALUES (1, 'USER');
INSERT INTO user_roles (user_id, roles)
VALUES (1, 'ADMIN');
INSERT INTO user_roles (user_id, roles)
VALUES (2, 'USER');
INSERT INTO user_roles (user_id, roles)
VALUES (3, 'USER');
INSERT INTO user_roles (user_id, roles)
VALUES (3, 'ADMIN_TENISTA');
INSERT INTO user_roles (user_id, roles)
VALUES (4, 'USER');
INSERT INTO user_roles (user_id, roles)
VALUES (4, 'ADMIN_TORNEO');



