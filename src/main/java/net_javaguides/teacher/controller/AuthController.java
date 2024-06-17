package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net_javaguides.teacher.Dto.Login.LoginDto;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.AuthResponse;
import net_javaguides.teacher.services.Security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth")

public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApiResponse<AuthResponse> postMethodName(@Valid @RequestBody LoginDto loginDto) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Logeado correctamente",
                authService.login(loginDto)
        );
    }

}
