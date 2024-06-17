package net_javaguides.teacher.repository;

import net_javaguides.teacher.entity.Users.Admin;
import net_javaguides.teacher.entity.Users.Docente;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente,Long> {
    @EntityGraph(attributePaths = {"user"})
    @Query("SELECT d FROM Docente d")
    Optional<Docente>  findByName(String name);
    Optional<Docente>  findByEmail(String email);
    Optional<Docente> findByPhone(String phone);
    Optional<Docente> findByCi(String ci);


}
