package net_javaguides.teacher.Dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DiaDto {
    @NotNull(message = "Ingrese un dia")
    private String name;
}
