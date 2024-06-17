package net_javaguides.teacher.repository.School;

import net_javaguides.teacher.entity.School.Dias;
import net_javaguides.teacher.entity.School.Grupo;
import net_javaguides.teacher.entity.School.Horario;
import net_javaguides.teacher.entity.School.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario,Long> {
    List<Horario> findByGrupoAndDias(Grupo grupo, Dias dias);
}
