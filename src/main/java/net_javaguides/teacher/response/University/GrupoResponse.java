package net_javaguides.teacher.response.University;

import lombok.Data;
import net_javaguides.teacher.entity.School.Grupo;

@Data
public class GrupoResponse {
    private Grupo grupo;

    public GrupoResponse(Grupo grupo) {
        this.grupo = grupo;
    }
}
