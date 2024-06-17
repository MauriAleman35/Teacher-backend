package net_javaguides.teacher.services.Interfaces;

import net_javaguides.teacher.Dto.SolicitudDto;
import net_javaguides.teacher.entity.School.SolicitudAsistencia;

import java.util.List;

public interface SolicitudAsistenciaService {
    SolicitudAsistencia createSolicitudAsistencia(SolicitudDto solicitudAsistenciaDto);
    SolicitudAsistencia updateSolicitudAsistencia(Long id, SolicitudDto solicitudAsistenciaDto);
    SolicitudAsistencia updateEstadoSolicitudAsistencia(Long id, boolean estado);
    void deleteSolicitudAsistencia(Long id);
    SolicitudAsistencia getSolicitudAsistenciaById(Long id);
    List<SolicitudAsistencia> getAllSolicitudesAsistencia();
}
