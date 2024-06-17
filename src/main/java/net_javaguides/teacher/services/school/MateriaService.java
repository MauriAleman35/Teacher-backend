package net_javaguides.teacher.services.school;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.MateriaDto;
import net_javaguides.teacher.Dto.ModuloDto;
import net_javaguides.teacher.entity.School.Carrera;
import net_javaguides.teacher.entity.School.Materia;
import net_javaguides.teacher.entity.School.Modulo;
import net_javaguides.teacher.entity.Users.Docente;
import net_javaguides.teacher.error.exception.BadRequestException;
import net_javaguides.teacher.error.exception.NotFoundException;
import net_javaguides.teacher.repository.School.CarreraRepository;
import net_javaguides.teacher.repository.School.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;



    @Autowired
    private CarreraRepository carreraRepository;

    public Materia materiaCreate(MateriaDto materiaDto){

        var carrera=this.carreraRepository.findById(materiaDto.getIdCarrera());
        var materiacreate= new Materia(
                materiaDto.getName(),
                materiaDto.getSigla(),
                materiaDto.getCredito(),
                carrera.get()

        );
        materiaRepository.save(materiacreate);
        return materiacreate;
    }
    public Page<Materia> findAllMateria(int skip, int limit){
        Pageable pageable= PageRequest.of(skip,limit);

        var materias=this.materiaRepository.findAll(pageable);
        return materias;
    }

    public Materia getMateriaById(Long id){
        return materiaRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe la Materia"));
    }

    @Transactional
    public void deleteMateria(Long id) {
        var findMateriaId = this.materiaRepository.findById(id);
        if(findMateriaId.isPresent()) {
            this.materiaRepository.deleteById(id);
        } else {
            throw new NotFoundException("Materia with id " + id + " not found");
        }
    }
    public Materia actualizarMateria(Long id, MateriaDto moduloDto){
        var findMateriaId=this.getMateriaById(id);
        //validar para datos ya ocupados
        Carrera carrera = this.carreraRepository.findById(moduloDto.getIdCarrera())
                .orElseThrow(() -> new BadRequestException("La carrera no se encuentra"));

        var materiaUpdate=findMateriaId;
        materiaUpdate.setNombre(moduloDto.getName());
        materiaUpdate.setSigla(moduloDto.getSigla());
        materiaUpdate.setCredito(moduloDto.getCredito());
        materiaUpdate.setCarrera(carrera);

        return this.materiaRepository.save(materiaUpdate);
    }


}
