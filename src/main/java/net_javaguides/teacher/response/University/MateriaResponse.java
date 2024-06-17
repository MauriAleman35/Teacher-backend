package net_javaguides.teacher.response.University;

import lombok.Data;

import net_javaguides.teacher.entity.School.Materia;

@Data
public class MateriaResponse {
     Materia materia;

    public MateriaResponse(Materia materia) {
        this.materia=materia;
    }
}
