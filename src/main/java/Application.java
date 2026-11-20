package com.example.reto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/*
 * Punto de entrada de la aplicación.
 * Escanea los paquetes de capas (application, domain, infrastructure) para que
 * los componentes de caché queden registrados en el contenedor de DI.
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
@ComponentScan(basePackages = {"com.example.reto", "application", "domain", "infrastructure"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}