package com.parcial3.festivos_api_application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parcial3.festivos_api_application.service.FestivoService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/festivos")
@CrossOrigin(
    origins = {
        "http://localhost:4200",
        "https://adolfo9castro.github.io"
    },
    methods = {RequestMethod.GET, RequestMethod.OPTIONS},
    allowedHeaders = "*",
    allowCredentials = "false"
)
public class FestivoController {
    private final FestivoService festivoService;

    public FestivoController(FestivoService festivoService) {
        this.festivoService = festivoService;
    }

    @Operation(
        summary = "Verificar si una fecha es festiva",
        description = "Verifica si la fecha proporcionada corresponde a un día festivo en Colombia",
        responses = {
            @ApiResponse(responseCode = "200", description = "Resultado de la validación",
                content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "400", description = "Fecha inválida",
                content = @Content(schema = @Schema(implementation = Map.class)))
        }
    )
    @GetMapping("/verificar/{year}/{mes}/{dia}")
    public ResponseEntity<Map<String, Object>> verificarFestivo(
        @Parameter(description = "Año (4 dígitos)", example = "2024", required = true)
        @PathVariable int year,
        
        @Parameter(description = "Mes (1-12)", example = "12", required = true)
        @PathVariable int mes,
        
        @Parameter(description = "Día (1-31)", example = "25", required = true)
        @PathVariable int dia) {
        
        try {
            boolean esFestivo = festivoService.esFestivo(year, mes, dia);
            
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", esFestivo ? "Es festivo" : "No es festivo");
            response.put("statusCode", HttpStatus.OK.value());
            
            return ResponseEntity.ok(response);
        } catch (DateTimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Fecha no válida");
            error.put("statusCode", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @Operation(
        summary = "Obtener festivos por año",
        description = "Retorna todos los días festivos en Colombia para un año específico",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de festivos",
                content = @Content(schema = @Schema(implementation = Map.class)))
        }
    )
    @GetMapping("/obtener/{year}")
    public ResponseEntity<Map<String, Object>> obtenerFestivos(
        @Parameter(description = "Año (4 dígitos)", example = "2024", required = true)
        @PathVariable int year) {
        
        List<Map<String, Object>> festivos = festivoService.obtenerFestivos(year);
        
        Map<String, Object> response = new HashMap<>();
        response.put("año", year);
        response.put("festivos", festivos);
        response.put("total", festivos.size());
        response.put("statusCode", HttpStatus.OK.value());
        
        return ResponseEntity.ok(response);
    }
}
