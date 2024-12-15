package com.eycrojas.gestor_proyectos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configura CORS globalmente
        registry.addMapping("/**")  // Aplícalo a todos los endpoints
                .allowedOrigins("http://localhost:4200") // Permite solicitudes desde este origen (puedes agregar más si es necesario)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*")  // Permite todos los encabezados
                .allowCredentials(true);  // Permite enviar credenciales (cookies, autenticación, etc.)
    }

}
