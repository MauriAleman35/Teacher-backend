package net_javaguides.teacher.repository.School;

import net_javaguides.teacher.entity.School.Grupo;
import net_javaguides.teacher.entity.School.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GrupoRepository extends JpaRepository<Grupo,Long> {
    Optional<Grupo> findByNombre(String nombre);
}
