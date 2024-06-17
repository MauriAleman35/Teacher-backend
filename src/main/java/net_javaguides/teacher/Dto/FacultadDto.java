package net_javaguides.teacher.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FacultadDto {
    @NotNull(message = "Ingrese un nombre")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String name;
}
