package net_javaguides.teacher.response;

import lombok.Data;
import net_javaguides.teacher.entity.Users.Docente;
import net_javaguides.teacher.entity.Users.User;

@Data
class UserResponse {
    private Long id;
    private String codigo;
    private String contraseña;
    private Long rolId;

    public UserResponse(User user) {
        this.id = user.getId();
        this.codigo = user.getCodigo();
        this.contraseña = user.getContraseña();
        this.rolId = user.getRol().getId();
    }
}