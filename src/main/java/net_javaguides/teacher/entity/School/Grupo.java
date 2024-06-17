package net_javaguides.teacher.entity.School;

import jakarta.persistence.*;
import lombok.*;
import net_javaguides.teacher.entity.Users.Docente;

@Data
@ToString @EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "grupo", schema = "public")

public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "periodo", length = 50, nullable = false)
    private String periodo;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    public Grupo(String nombre,String periodo, Docente idDocente,Materia materia){
        super();
        this.nombre = nombre;
        this.periodo=periodo;
        this.materia=materia;
        this.docente=idDocente;
    }
}
