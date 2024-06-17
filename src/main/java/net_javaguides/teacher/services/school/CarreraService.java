package net_javaguides.teacher.services.school;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.CarreraDto;
import net_javaguides.teacher.Dto.MateriaDto;
import net_javaguides.teacher.entity.School.Carrera;
import net_javaguides.teacher.entity.School.Facultad;
import net_javaguides.teacher.entity.School.Materia;
import net_javaguides.teacher.error.exception.BadRequestException;
import net_javaguides.teacher.error.exception.NotFoundException;
import net_javaguides.teacher.repository.School.CarreraRepository;
import net_javaguides.teacher.repository.School.FacultadRepository;
import net_javaguides.teacher.repository.School.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarreraService {
    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private CarreraRepository carreraRepository;
    public Carrera carreraCreate(CarreraDto carreraDto){

        var facultad=this.facultadRepository.findById(1L);
        var carreracreate= new Carrera(
               carreraDto.getNombre(),
                carreraDto.getCodigo(),
                facultad.get()

        );
        carreraRepository.save(carreracreate);
        return carreracreate;
    }
    public Page<Carrera> findAllCarrera(int skip, int limit){
        Pageable pageable= PageRequest.of(skip,limit);

        var carreras=this.carreraRepository.findAll(pageable);
        return carreras;
    }

    public Carrera getCarreraById(Long id){
        return this.carreraRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe la Carrera"));
    }

    @Transactional
    public void deleteCarrera(Long id) {
        var findCarreraId = this.carreraRepository.findById(id);
        if(findCarreraId.isPresent()) {
            this.carreraRepository.deleteById(id);
        } else {
            throw new NotFoundException("Carrera with id " + id + " not found");
        }
    }
    public Carrera actualizarCarrera(Long id, CarreraDto carreraDto){
        var findCarreraId=this.getCarreraById(id);
        //validar para datos ya ocupados
        Facultad facultad = this.facultadRepository.findById(1L)
                .orElseThrow(() -> new BadRequestException("La Facultad no se encuentra"));

        var carreraUpdate=findCarreraId;
        carreraUpdate.setNombre(carreraDto.getNombre());
        carreraUpdate.setCodigo(carreraDto.getCodigo());
        carreraUpdate.setFacultad(facultad);
        return this.carreraRepository.save(carreraUpdate);
    }

}
