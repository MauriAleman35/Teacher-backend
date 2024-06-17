package net_javaguides.teacher.entity.Users;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net_javaguides.teacher.Dto.user.AdminDto;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "admin", schema = "public")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "name",
            length = 75,
            nullable = false
    )
    private String name;
    @Column(
            name = "ci",
            length = 50,
            nullable = false
    )
    private String ci;



    @Column(
            name = "email",
            length = 50,
            nullable = false
    )
    private String email;



    @Column(
            name = "phone",
            length = 8,
            nullable = false
    )
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Admin(String name, String ci, String email, String phone, User user){
        this.name=name;
        this.ci=ci;
        this.email=email;
        this.phone=phone;
        this.user=user;


    }
}
