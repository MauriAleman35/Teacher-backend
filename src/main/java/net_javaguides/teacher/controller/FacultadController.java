package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.FacultadDto;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.University.FacultadResponse;
import net_javaguides.teacher.services.school.FacultadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facultad")
public class FacultadController {
    @Autowired
    private FacultadService facultadService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<FacultadResponse> postMethodName(@Valid @RequestBody FacultadDto facultadDtoDto) {

        return  new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "USUARIO CREADO",
                new FacultadResponse(facultadService.facultadCreate(facultadDtoDto))
        );
    }
}
