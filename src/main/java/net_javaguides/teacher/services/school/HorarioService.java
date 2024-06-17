package net_javaguides.teacher.services.school;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.GrupoDto;
import net_javaguides.teacher.Dto.HorarioDto;
import net_javaguides.teacher.Dto.MateriaDto;
import net_javaguides.teacher.entity.School.*;
import net_javaguides.teacher.error.exception.BadRequestException;
import net_javaguides.teacher.error.exception.NotFoundException;
import net_javaguides.teacher.repository.School.AulaRepository;
import net_javaguides.teacher.repository.School.DiaRepository;
import net_javaguides.teacher.repository.School.GrupoRepository;
import net_javaguides.teacher.repository.School.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private DiaRepository diaRepository;

    public Horario horarioCreate(HorarioDto horarioDto){

        var Aula=aulaRepository.findById(horarioDto.getIdAula());
        if(!Aula.isPresent()){
            throw new BadRequestException(
                    "El aula No existe"
            );
        }

        var grupo=grupoRepository.findById(horarioDto.getIdGrupo());
        if(!grupo.isPresent()){
            throw new BadRequestException(
                    "El grupo no existe"
            );
        }

        var dia=diaRepository.findById(horarioDto.getIdDia());
        if(!dia.isPresent()){
            throw new BadRequestException(
                    "El dia no existe"
            );
        }

        List<Horario> horariosExistentes = horarioRepository.findByGrupoAndDias(grupo.get(), dia.get());
        for (Horario horarioExistente : horariosExistentes) {
            if (horarioDto.getHoraInicio().isBefore(horarioExistente.getHoraFinal()) &&
                    horarioDto.getHoraFin().isAfter(horarioExistente.getHoraInicio())) {
                throw new BadRequestException(
                        "El grupo ya tiene un horario asignado en este día y hora"
                );
            }
        }

        var HorarioCreate= new Horario(
                horarioDto.getHoraInicio(),
                horarioDto.getHoraFin(),
                grupo.get(),
                Aula.get(),
                dia.get()
        );
        return  horarioRepository.save(HorarioCreate);

    }

    @Transactional
    public List<Horario> findAllHorario(){


        var horarios=this.horarioRepository.findAll();
        return horarios;
    }
    @Transactional
    public Horario getHorarioById(Long id){
        return horarioRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe el Horario"));
    }
    @Transactional
    public void deleteHorario(Long id) {
        var findHorarioId = this.horarioRepository.findById(id);
        if(findHorarioId.isPresent()) {
            this.horarioRepository.deleteById(id);
        } else {
            throw new NotFoundException("Horario with id " + id + " not found");
        }
    }
    /*
    *   public Materia actualizarMateria(Long id, MateriaDto moduloDto){
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

    *
    *
    *
    * */



}
