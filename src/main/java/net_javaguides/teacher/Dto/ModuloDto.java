package net_javaguides.teacher.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ModuloDto {
    @NotNull(message = "Ingrese el Numero de un modulo")
    private String numero;


    @NotNull(message = "Ingrese una direccion a su modulo")
    @Size(min = 5, message = "El nombre debe tener al menos 2 caracteres")
    private String direccion;

    private  long idFacultad;

}
