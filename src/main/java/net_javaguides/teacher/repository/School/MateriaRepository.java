package net_javaguides.teacher.repository.School;

import net_javaguides.teacher.entity.School.Materia;
import net_javaguides.teacher.entity.School.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MateriaRepository extends JpaRepository<Materia,Long> {
    public List<Materia> findAll();
}
