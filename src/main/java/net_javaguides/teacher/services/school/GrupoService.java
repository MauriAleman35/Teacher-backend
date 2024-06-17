package net_javaguides.teacher.services.school;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.GrupoDto;
import net_javaguides.teacher.Dto.ModuloDto;
import net_javaguides.teacher.entity.School.Grupo;
import net_javaguides.teacher.entity.School.Materia;
import net_javaguides.teacher.entity.School.Modulo;
import net_javaguides.teacher.entity.Users.Docente;
import net_javaguides.teacher.error.exception.BadRequestException;
import net_javaguides.teacher.error.exception.NotFoundException;
import net_javaguides.teacher.repository.DocenteRepository;
import net_javaguides.teacher.repository.School.GrupoRepository;
import net_javaguides.teacher.repository.School.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GrupoService {
    @Autowired
    private DocenteRepository docenteRepository;
    @Autowired
    private MateriaRepository materiaRepository;


    private GrupoRepository grupoRepo;

    public Grupo grupoCreate(GrupoDto grupoDto){

        var doc=docenteRepository.findById(grupoDto.getIdDocente());
        if(!doc.isPresent()){
            throw new BadRequestException(
                    "El docente no se encuentra"
            );
        }

        var materia=materiaRepository.findById(grupoDto.getIdMateria());
        if(!materia.isPresent()){
            throw new BadRequestException(
                    "La materia no se encuentra"
            );
        }
         var nameGrupo=grupoRepo.findByNombre(grupoDto.getNombre());
        if(nameGrupo.isPresent()){
            throw new BadRequestException(
                    "El grupo ya existe"
            );
        }

        var grupoCreate= new Grupo(
                grupoDto.getNombre(),
                grupoDto.getPeriodo(),
                doc.get(),
                materia.get()
        );
        return  grupoRepo.save(grupoCreate);

    }
    @Transactional
    public List<Grupo> findAll(){
        return (List<Grupo>) grupoRepo.findAll();
    }

    public Grupo findGrupoId(Long id){
        var findGrupo=this.grupoRepo.findById(id);
        if(!findGrupo.isPresent()){
            throw new NotFoundException(
                    "Grupo id " +id +" not found"
            );
        }
        return findGrupo.get();
    }

    @Transactional
    public void deleteGrupo(Long id) {
        var findModuloId = this.grupoRepo.findById(id);
        if(findModuloId.isPresent()) {
            this.grupoRepo.deleteById(id);
        } else {
            throw new NotFoundException("Modulo with id " + id + " not found");
        }
    }

    public Grupo actualizarGrupo(Long id, GrupoDto grupoDto){
        var findGrupoId=findGrupoId(id);
        var GrupoUpdate=findGrupoId;

        Docente docente = docenteRepository.findById(grupoDto.getIdDocente())
                .orElseThrow(() -> new BadRequestException("El docente no se encuentra"));

        // Verificar que la Materia existe
        Materia materia = materiaRepository.findById(grupoDto.getIdMateria())
                .orElseThrow(() -> new BadRequestException("La materia no se encuentra"));

        GrupoUpdate.setNombre(grupoDto.getNombre());
        GrupoUpdate.setPeriodo(grupoDto.getPeriodo());
        GrupoUpdate.setDocente(docente);
        GrupoUpdate.setMateria(materia);
        return grupoRepo.save(GrupoUpdate);
    }



}
