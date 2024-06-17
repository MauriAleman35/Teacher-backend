package net_javaguides.teacher.entity.School;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net_javaguides.teacher.entity.Users.Docente;

import java.time.LocalDate;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "solicitudAsistencia", schema = "public")
public class SolicitudAsistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "estado", nullable = false)
    private boolean estado=false;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;


    @ManyToOne
    @JoinColumn(name = "docente_id", nullable = false)
    private Docente docente;

    public SolicitudAsistencia(LocalDate fechaInicio,LocalDate fechaFin, String motivo, boolean estado,
                               LocalDate fechaSolicitud,
        Docente docente
    ){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.motivo = motivo;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
        this.docente = docente;
    }

}
