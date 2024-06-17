package net_javaguides.teacher.entity.School;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "aula", schema = "public")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", nullable = false, length = 100)
    private String numero;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "modulo_id", nullable = false)
    private Modulo modulo;

    public Aula(String numero, Integer capacidad, Modulo modulo){
        this.numero=numero;
        this.capacidad=capacidad;
        this.modulo=modulo;
    }
}
