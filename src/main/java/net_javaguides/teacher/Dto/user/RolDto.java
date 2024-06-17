package net_javaguides.teacher.Dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RolDto {
    @NotNull(message = "Ingrese un Rol")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String name;

}
