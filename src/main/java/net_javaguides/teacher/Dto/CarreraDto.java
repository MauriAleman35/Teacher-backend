package net_javaguides.teacher.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarreraDto {

    @NotNull(message = "Ingrese un nombre")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String nombre;

    @NotNull(message = "Ingrese un codigo para la carrera")
    private String codigo;


}
