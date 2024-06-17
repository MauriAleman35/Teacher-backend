package net_javaguides.teacher.repository;

import net_javaguides.teacher.entity.Users.Admin;
import net_javaguides.teacher.entity.Users.Docente;
import net_javaguides.teacher.entity.Users.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin> findByCi(String ci);
    Optional<Admin>  findByEmail(String email);
    Optional<Admin> findByPhone(String phone);

}
