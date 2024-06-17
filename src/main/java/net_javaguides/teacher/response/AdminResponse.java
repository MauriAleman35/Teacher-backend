package net_javaguides.teacher.response;

import lombok.Data;
import net_javaguides.teacher.entity.Users.Admin;
import net_javaguides.teacher.entity.Users.Docente;

@Data
public class AdminResponse {
    private Admin admin;

    private UserResponse userResponse;
    public AdminResponse(Admin admin) {
        this.admin=admin;


    }
}
