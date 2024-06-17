package net_javaguides.teacher.response;

import lombok.Data;
import net_javaguides.teacher.entity.Users.Docente;



@Data
public class DocenteResponse {
    private Docente docente;

    private UserResponse userResponse;
    public DocenteResponse(Docente docente) {
        this.docente=docente;

        
    }
}
