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
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "codigo",
            length = 50,
            nullable = false
    )
    private String codigo;
    @Column(
            name = "contraseña",
            length = 250,
            nullable = false
    )
    private String contraseña;



    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;


    public User(String codigo, String contraseña, Rol rol){
        super();
        this.codigo=codigo;
        this.contraseña=contraseña;
        this.rol=rol;


    }

}
