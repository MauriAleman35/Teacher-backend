package net_javaguides.teacher.repository.School;

import net_javaguides.teacher.entity.School.SolicitudAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudLicenciaRepository extends JpaRepository<SolicitudAsistencia,Long> {
}
