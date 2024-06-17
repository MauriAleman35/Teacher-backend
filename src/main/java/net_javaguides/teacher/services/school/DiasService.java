package net_javaguides.teacher.services.school;

import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.user.DiaDto;
import net_javaguides.teacher.entity.School.Dias;
import net_javaguides.teacher.entity.School.Facultad;
import net_javaguides.teacher.repository.School.DiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiasService {
    @Autowired
    DiaRepository diaRepository;



    public Dias DiasCreate(DiaDto diaDto){

        var diaCreate = new Dias(
           diaDto.getName()
        );
        return diaRepository.save(diaCreate);
    }
    public List<Dias> getDias(){
        return this.diaRepository.findAll();
    }
}
