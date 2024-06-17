package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.FacultadDto;
import net_javaguides.teacher.Dto.user.DiaDto;
import net_javaguides.teacher.entity.School.Dias;
import net_javaguides.teacher.entity.School.Horario;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.University.DiasResponse;
import net_javaguides.teacher.response.University.FacultadResponse;
import net_javaguides.teacher.services.school.DiasService;
import net_javaguides.teacher.services.school.FacultadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dias")
public class DiasController {
    @Autowired
    private DiasService diasService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<DiasResponse> postMethodName(@Valid @RequestBody DiaDto diaDto) {

        return  new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Dia CREADO",
                new DiasResponse(diasService.DiasCreate(diaDto))
        );
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Dias> getDiaAll(){
        return this.diasService.getDias();
    }

}
