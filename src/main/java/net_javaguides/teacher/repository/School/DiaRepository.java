package net_javaguides.teacher.repository.School;

import net_javaguides.teacher.entity.School.Dias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaRepository extends JpaRepository<Dias,Long> {
}
