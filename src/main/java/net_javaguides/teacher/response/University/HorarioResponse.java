package net_javaguides.teacher.response.University;

import lombok.Data;
import net_javaguides.teacher.entity.School.Horario;


@Data
public class HorarioResponse {
    private Horario horario;
    public HorarioResponse(Horario horario){
        this.horario=horario;
    }
}
