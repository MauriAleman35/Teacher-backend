package net_javaguides.teacher.response.University;

import lombok.Data;
import net_javaguides.teacher.entity.School.Aula;
@Data
public class AulaResponse {
    private Aula aula;
    public AulaResponse(Aula aula){
        this.aula=aula;
    }
}
