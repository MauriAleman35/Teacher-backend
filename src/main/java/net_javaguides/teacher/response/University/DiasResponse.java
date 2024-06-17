package net_javaguides.teacher.response.University;

import lombok.Data;
import net_javaguides.teacher.entity.School.Dias;
import net_javaguides.teacher.entity.School.Facultad;
@Data
public class DiasResponse {
    private Dias dias;

    public DiasResponse(Dias dias) {
        this.dias =dias;
    }
}
