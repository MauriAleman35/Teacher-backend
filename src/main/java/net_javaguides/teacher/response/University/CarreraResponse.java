package net_javaguides.teacher.response.University;

import lombok.Data;
import net_javaguides.teacher.entity.School.Carrera;

@Data
public class CarreraResponse {
    private Carrera carrera;
    public  CarreraResponse(Carrera carrera){
        this.carrera=carrera;
    }
}
