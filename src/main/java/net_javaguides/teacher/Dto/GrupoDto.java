package net_javaguides.teacher.Dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GrupoDto {

    @NotNull(message = "Ingrese un nombre")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String nombre;

    @NotNull(message = "Ingrese un periodo")

    private String periodo;

    @NotNull(message = "Ingrese el docente")

    @NotNull(message = "Ingrese el idDocente")
    private Long idDocente;
    @NotNull(message = "Ingrese el idMateria")
    private Long idMateria;
}
