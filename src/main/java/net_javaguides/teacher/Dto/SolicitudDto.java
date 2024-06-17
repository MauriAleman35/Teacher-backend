package net_javaguides.teacher.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net_javaguides.teacher.entity.Users.Docente;

import java.time.LocalDate;

@Data
public class SolicitudDto {

    @NotNull(message = "ingrese una fecha Inicio de la solicitud")
    private LocalDate fechaInicio;


    @NotNull(message = "ingrese una fecha fin de la solicitud")
    private LocalDate fechaFin;

    @NotNull(message = "ingrese un motivo para la solicitud ")
    private String motivo;

    @NotNull(message = "ingrese un estado inicial a la solicitud")
    private boolean estado;




    @NotNull(message = "ingrese el docente de la solicitud")
    private Long idDocente;
}
