package net_javaguides.teacher.Dto.Login;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {

    @NotNull(message = "Ingrese un codigo Valido")

    private String codigo;

    @NotNull(message = "Ingrese un codigo en el campo contrasenha")
    private String contraseña;

}
