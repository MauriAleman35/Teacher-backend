package net_javaguides.teacher.entity.School;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "horario", schema = "public")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_final", nullable = false)
    private LocalTime horaFinal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aula_id")
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "dia_id")
    private Dias dias;

    public Horario(LocalTime horaInicio, LocalTime horaFinal, Grupo grupo, Aula aula,Dias dias){

        this.horaInicio=horaInicio;
        this.horaFinal=horaFinal;
        this.grupo=grupo;
        this.aula=aula;
        this.dias=dias;

    }

}
