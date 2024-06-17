package net_javaguides.teacher.entity.School;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net_javaguides.teacher.entity.Users.Docente;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "carrera", schema = "public")
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 75, nullable = false)
    private String nombre;

    @Column(name = "codigo", length = 75, nullable = false)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "facultad_id")
    private Facultad facultad;

    public Carrera(String nombre,  String codigo, Facultad facultad){
        this.nombre=nombre;
        this.codigo=codigo;
        this.facultad=facultad;
    }

}
