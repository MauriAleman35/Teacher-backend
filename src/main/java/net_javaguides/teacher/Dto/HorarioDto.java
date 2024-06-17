package net_javaguides.teacher.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

/*
* Ideas para implementacion de horariop
* */
//ejecucion de tarea de segundo plano
//antes de los 15 min notificar que hay clases
//si no hay asistencia, se pone falta
//15 min despues que acabe la clase poner que no dio clase el tipo

@Data
public class HorarioDto {

    @NotNull(message = "Ingrese una hora de inicio de la clase")
    private LocalTime horaInicio;
    @NotNull(message = "Ingrese una hora fin de la clase")
    private LocalTime horaFin;

    @NotNull(message = "Ingrese el grupo del horario")
    private Long idGrupo;
    @NotNull(message = "Ingrese el aula del horario")
    private Long idAula;

    @NotNull(message = "Ingrese el dia del horario")
    private Long idDia;


}
