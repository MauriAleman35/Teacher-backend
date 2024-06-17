package net_javaguides.teacher.response.University;

import lombok.Data;
import net_javaguides.teacher.entity.School.SolicitudAsistencia;
@Data
public class SolicitudResponse {
    private SolicitudAsistencia solicitudAsistencia;
    public SolicitudResponse(SolicitudAsistencia solicitudAsistencia){
        this.solicitudAsistencia=solicitudAsistencia;
    }
}
