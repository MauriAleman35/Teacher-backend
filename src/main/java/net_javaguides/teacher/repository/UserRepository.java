package net_javaguides.teacher.repository;

import net_javaguides.teacher.entity.Users.Admin;
import net_javaguides.teacher.entity.Users.Docente;
import net_javaguides.teacher.entity.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User>  findByCodigo(String codigo);
}
