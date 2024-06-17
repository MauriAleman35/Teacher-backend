package net_javaguides.teacher.entity.School;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "materia", schema = "public")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sigla", length = 50, nullable = false)
    private String sigla;

    @Column(name = "nombre", length = 75, nullable = false)
    private String nombre;

    @Column(name = "credito")
    private Integer credito;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;


    public Materia(String sigla,String nombre, Integer credito, Carrera carrera){
        this.sigla=sigla;
        this.nombre=nombre;
        this.credito=credito;
        this.carrera=carrera;
    }
}
