package com.parcial3.festivos_api_application.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API de Días Festivos - ITM",
        version = "2.0.0",
        description = "API para validación y consulta de días festivos en Colombia",
        contact = @Contact(
            name = "Adolfo Castro Noreña",
            email = "jairocastro208461@correo.itm.edu.co",
            url = "https://github.com/adolfo9castro/diseno_software_itm"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Servidor local"),
    }
)
public class OpenApiConfig {
}