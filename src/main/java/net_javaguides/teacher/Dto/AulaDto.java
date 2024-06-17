package net_javaguides.teacher.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AulaDto {
    @NotNull(message = "Ingrese el Numero del aula")
    private String numero;


    @NotNull(message = "Ingrese la capacidad de alumnos en el Aula")

    private Integer capacidad;

    @NotNull(message = "Ingrese el modulo para el aula")
    private  long idModulo;
}
