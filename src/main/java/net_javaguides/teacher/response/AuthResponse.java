package net_javaguides.teacher.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net_javaguides.teacher.entity.Users.Admin;
import net_javaguides.teacher.entity.Users.User;


@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Object userDetails;
}
