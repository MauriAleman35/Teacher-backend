package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.AulaDto;
import net_javaguides.teacher.Dto.HorarioDto;
import net_javaguides.teacher.entity.School.Aula;
import net_javaguides.teacher.entity.School.Grupo;
import net_javaguides.teacher.entity.School.Horario;
import net_javaguides.teacher.entity.School.Modulo;
import net_javaguides.teacher.repository.School.HorarioRepository;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.University.AulaResponse;
import net_javaguides.teacher.response.University.HorarioResponse;
import net_javaguides.teacher.services.school.AulaService;
import net_javaguides.teacher.services.school.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horario")
public class HorarioController {
    @Autowired
    private HorarioService horarioService;

    @PostMapping("/addHorario")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<HorarioResponse> postHorario(@Valid @RequestBody HorarioDto horarioDto){
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Horario Creado",
                new HorarioResponse(horarioService.horarioCreate(horarioDto))
        );
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Horario> getHorarioAll(){
        return this.horarioService.findAllHorario();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable Long id){
        Horario horario=horarioService.getHorarioById(id);
        return ResponseEntity.ok(horario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteHorario(@PathVariable Long id) {
        horarioService.deleteHorario(id);
    }



}
