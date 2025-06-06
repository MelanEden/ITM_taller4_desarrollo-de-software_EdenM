package com.parcial3.festivos_api_application.model;

import lombok.AllArgsConstructor;
import lombok.Data; // @Data incluye @Getter, @Setter, @EqualsAndHashCode, @ToString
import lombok.NoArgsConstructor;

/**
 * Modelo que representa un día festivo.
 */
@Data // Esta anotación generará los getters y setters automáticamente
@AllArgsConstructor // Este generará el constructor con todos los argumentos (Integer dia, Integer mes, String nombre, Integer tipo, Integer diasPascua)
@NoArgsConstructor // Este generará el constructor sin argumentos (Festivo())
public class Festivo {
    /**
     * Día del mes (1-31) o null si depende de Pascua
     */
    private Integer dia; // Debe ser Integer para aceptar null

    /**
     * Mes del año (1-12) o null si depende de Pascua
     */
    private Integer mes; // Debe ser Integer para aceptar null

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
    private Integer tipo; // Debe ser Integer para aceptar null en el constructor si lo pasas así

    /**
     * Días relativos a Pascua (para tipos 3 y 4)
     */
    private Integer diasPascua; // Debe ser Integer para aceptar null
}