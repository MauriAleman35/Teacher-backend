package net_javaguides.teacher.entity.School;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net_javaguides.teacher.entity.Users.Docente;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "modulo", schema = "public")
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", length = 50, nullable = false)
    private String numero;

    @Column(name = "direccion", length = 150, nullable = false)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "facultad_id")
    private Facultad facultad;

    public Modulo(String numero, String direccion, Facultad facultad){
        this.numero=numero;
        this.direccion=direccion;
        this.facultad=facultad;
    }
}
