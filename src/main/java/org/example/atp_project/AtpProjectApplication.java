package org.example.atp_project;

import org.example.atp_project.almacenamiento.StorageService;
import org.example.atp_project.repos.TenistaRepo;
import org.example.atp_project.service.tenista.TenistaServicioImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AtpProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        //INICIO DE LA APLICACIÓN SPRING BOOT
        SpringApplication.run(AtpProjectApplication.class, args);

    }

    @Override
    public void run(String... args){
        //AQUÍ SE PUEDE EJECUTAR CÓDIGO AL ARRANCAR LA APLICACIÓN
        System.out.println("\uD83E\uDD4E\uD83E\uDD4EJUEGO, SET Y PARTIDO, " +
                "LA APLICACIÓN YA ESTA ARRANCADA\uD83C\uDFBE");

    }



}
