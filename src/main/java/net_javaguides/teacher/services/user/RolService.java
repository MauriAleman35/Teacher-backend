package net_javaguides.teacher.services.user;

import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.user.RolDto;
import net_javaguides.teacher.entity.Users.Rol;
import net_javaguides.teacher.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public Rol rolCreate(RolDto rolDto){
        var rolpost=new Rol(
            rolDto.getName()
        );
        rolRepository.save(rolpost);
        return rolpost;
    }
}
