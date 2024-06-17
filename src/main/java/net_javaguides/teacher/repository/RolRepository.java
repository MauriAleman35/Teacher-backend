package net_javaguides.teacher.repository;

import net_javaguides.teacher.entity.Users.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long> {
}
