package net_javaguides.teacher.entity.Users;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "rol", schema = "public")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "nombre",
            length = 50,
            nullable = false
    )
    private String nombre;

    public Rol(String nombre){
        this.nombre=nombre;
    }
}
