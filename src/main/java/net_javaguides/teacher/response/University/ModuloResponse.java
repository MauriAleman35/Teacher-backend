package net_javaguides.teacher.response.University;

import lombok.Data;
import net_javaguides.teacher.entity.School.Modulo;

@Data
public class ModuloResponse {
    Modulo modulo;
    public ModuloResponse(Modulo modulo){
        this.modulo=modulo;
    }
}
