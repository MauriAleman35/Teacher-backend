package net_javaguides.teacher.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MateriaDto {


    @NotNull(message = "Ingrese un periodo")
    private String sigla;


    @NotNull(message = "Ingrese un nombre")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String name;

    @NotNull(message = "Ingrese un credito para la materia")
    private Integer credito;

    @NotNull(message = "Ingrese el idCarrera")
    private Long idCarrera;
}
