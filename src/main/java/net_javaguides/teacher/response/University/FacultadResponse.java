package net_javaguides.teacher.response.University;

import lombok.Data;
import net_javaguides.teacher.entity.School.Facultad;
import net_javaguides.teacher.entity.School.Grupo;

@Data
public class FacultadResponse {
    private Facultad facultad;

    public FacultadResponse(Facultad facultad) {
        this.facultad = facultad;
    }
}
