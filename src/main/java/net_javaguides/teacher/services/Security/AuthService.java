package net_javaguides.teacher.services.Security;

import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.Login.LoginDto;
import net_javaguides.teacher.Security.SecurityUser;
import net_javaguides.teacher.error.exception.NotFoundException;
import net_javaguides.teacher.error.exception.UnauthorizedException;
import net_javaguides.teacher.repository.AdminRepository;
import net_javaguides.teacher.repository.DocenteRepository;
import net_javaguides.teacher.repository.UserRepository;
import net_javaguides.teacher.response.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final DocenteRepository docenteRepository;
    private final AdminRepository adminRepository;

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginDto loginDto){

        var user = userRepository.findByCodigo(loginDto.getCodigo());
        var DocenteUser=docenteRepository.findByCi(user.get().getCodigo());
        var adminUser=this.adminRepository.findByCi(user.get().getCodigo());
        if(!user.isPresent()){
            throw new NotFoundException("El codigo no se encuentra disponible");
        }

                ;
        if(adminUser.isPresent()){
            var userget=adminUser.get();
        }else if (DocenteUser.isPresent()) {
            var userget = DocenteUser.get();

        }

        if (!passwordEncoder.matches(loginDto.getContraseña(), user.get().getContraseña())) {
            throw new UnauthorizedException("Bad credentials");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getCodigo(), loginDto.getContraseña()));
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException(e.getLocalizedMessage());
        }

        UserDetails userDetails = user.map(SecurityUser::new).orElseThrow();
        String token = jwtService.getToken(userDetails);

        Object userDetail;

        if (adminUser.isPresent()) {

            userDetail = adminUser.get();
        } else if (DocenteUser.isPresent()) {

            userDetail = DocenteUser.get();
        } else {
            throw new NotFoundException("El usuario no es ni administrador ni docente");
        }

        return new AuthResponse(
                token,
                userDetail

        );
    }
    /*
    *  public boolean validatePassword(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
    * */

}
