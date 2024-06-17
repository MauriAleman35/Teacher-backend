package net_javaguides.teacher.services.school;

import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.FacultadDto;

import net_javaguides.teacher.entity.School.Facultad;

import net_javaguides.teacher.repository.School.FacultadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FacultadService {
    @Autowired
    private FacultadRepository facultadRepository;




    public Facultad facultadCreate(FacultadDto facultadDto){

        var facultadCreate= new Facultad(

                facultadDto.getName()
        );
        return  facultadRepository.save(facultadCreate);
    }
}
