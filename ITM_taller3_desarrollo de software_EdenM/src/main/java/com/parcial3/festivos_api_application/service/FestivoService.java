package com.parcial3.festivos_api_application.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.parcial3.festivos_api_application.model.Festivo;
import com.parcial3.festivos_api_application.util.PascuaCalculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FestivoService {
    private final List<Festivo> festivos = new ArrayList<>();

    @PostConstruct
    public void inicializarFestivos() {
        festivos.addAll(List.of(
            new Festivo(1, 1, "Año nuevo", 1, null), // Corregido: eliminado 'dia:', 'mes:', etc.
            new Festivo(6, 1, "Santos Reyes", 2, null),
            new Festivo(19, 3, "San José", 2, null),
            new Festivo(null, null, "Jueves Santo", 3, -3),
            new Festivo(null, null, "Viernes Santo", 3, -2),
            new Festivo(null, null, "Domingo de Pascua", 3, 0),
            new Festivo(1, 5, "Día del Trabajo", 1, null),
            new Festivo(null, null, "Ascensión del Señor", 4, 40),
            new Festivo(null, null, "Corpus Christi", 4, 61),
            new Festivo(null, null, "Sagrado Corazón de Jesús", 4, 68),
            new Festivo(29, 6, "San Pedro y San Pablo", 2, null),
            new Festivo(20, 7, "Independencia Colombia", 1, null),
            new Festivo(7, 8, "Batalla de Boyacá", 1, null),
            new Festivo(15, 8, "Asunción de la Virgen", 2, null),
            new Festivo(12, 10, "Día de la Raza", 2, null),
            new Festivo(1, 11, "Todos los santos", 2, null),
            new Festivo(11, 11, "Independencia de Cartagena", 2, null),
            new Festivo(8, 12, "Inmaculada Concepción", 1, null),
            new Festivo(25, 12, "Navidad", 1, null)
        ));
    }

    public boolean esFestivo(int year, int mes, int dia) {
        LocalDate fecha = LocalDate.of(year, mes, dia);
        LocalDate pascua = PascuaCalculator.calcularDomingoPascua(year);
        return festivos.stream().anyMatch(festivo -> {
            LocalDate fechaFestivo = calcularFechaFestivo(festivo, year, pascua);
            return fechaFestivo != null && fechaFestivo.equals(fecha);
        });
    }

    public List<Map<String, Object>> obtenerFestivos(int year) {
        LocalDate pascua = PascuaCalculator.calcularDomingoPascua(year);
        List<Map<String, Object>> resultado = new ArrayList<>();
        
        festivos.forEach(festivo -> {
            LocalDate fecha = calcularFechaFestivo(festivo, year, pascua);
            if (fecha != null) {
                Map<String, Object> item = new HashMap<>();
                item.put("nombre", festivo.getNombre());
                item.put("fecha", fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                item.put("dia", traducirDiaSemana(fecha.getDayOfWeek()));
                resultado.add(item);
            }
        });
        
        return resultado;
    }

    private LocalDate calcularFechaFestivo(Festivo festivo, int year, LocalDate pascua) {
        return switch (festivo.getTipo()) {
            case 1 -> LocalDate.of(year, festivo.getMes(), festivo.getDia());
            case 2 -> ajustarALunes(LocalDate.of(year, festivo.getMes(), festivo.getDia()));
            case 3 -> pascua.plusDays(festivo.getDiasPascua());
            case 4 -> ajustarALunes(pascua.plusDays(festivo.getDiasPascua()));
            default -> null;
        };
    }

    private LocalDate ajustarALunes(LocalDate fecha) {
        return fecha.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
    }

    private String traducirDiaSemana(DayOfWeek dia) {
        return switch (dia) {
            case MONDAY -> "Lunes";
            case TUESDAY -> "Martes";
            case WEDNESDAY -> "Miércoles";
            case THURSDAY -> "Jueves";
            case FRIDAY -> "Viernes";
            case SATURDAY -> "Sábado";
            case SUNDAY -> "Domingo";
        };
    }
}