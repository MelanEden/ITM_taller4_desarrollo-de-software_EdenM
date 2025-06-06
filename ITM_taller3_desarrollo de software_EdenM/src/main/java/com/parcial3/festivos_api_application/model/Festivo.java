package com.parcial3.festivos_api_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo que representa un día festivo.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Festivo {
    /**
     * Día del mes (1-31) o null si depende de Pascua
     */
    private Integer dia;
    
    /**
     * Mes del año (1-12) o null si depende de Pascua
     */
    private Integer mes;
    
    /**
     * Nombre del festivo
     */
    private String nombre;
    
    /**
     * Tipo de festivo:
     * 1 - Fecha fija
     * 2 - Fecha móvil (lunes siguiente)
     * 3 - Relativo a Pascua
     * 4 - Relativo a Pascua (lunes siguiente)
     */
    private Integer tipo;
    
    /**
     * Días relativos a Pascua (para tipos 3 y 4)
     */
    private Integer diasPascua;
}