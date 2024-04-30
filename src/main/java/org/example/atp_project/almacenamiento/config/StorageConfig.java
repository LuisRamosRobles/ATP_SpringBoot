package org.example.atp_project.almacenamiento.config;

import org.example.atp_project.almacenamiento.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class StorageConfig {

    private final Logger logger = LoggerFactory.getLogger(StorageConfig.class);

    @Bean
    public CommandLineRunner init(StorageService storageService) {
        return args -> {
            // Inicializamos el servicio de ficheros
            logger.info("Borrando todos los archivos.");
            storageService.deleteAll();
            storageService.init();

        };

    }

}
