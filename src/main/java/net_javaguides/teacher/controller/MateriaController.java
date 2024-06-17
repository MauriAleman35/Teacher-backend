package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.MateriaDto;
import net_javaguides.teacher.Dto.ModuloDto;
import net_javaguides.teacher.entity.School.Materia;
import net_javaguides.teacher.entity.School.Modulo;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.University.MateriaResponse;
import net_javaguides.teacher.response.University.ModuloResponse;
import net_javaguides.teacher.services.school.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materia")
public class MateriaController {
    @Autowired
    private MateriaService materiaService;

    @PostMapping("/addMateria")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MateriaResponse> postMateria(@Valid @RequestBody MateriaDto materiaDto){
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Materia CREADO",
                new MateriaResponse(materiaService.materiaCreate(materiaDto))
        );


    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Page<Materia>> getMethodName(
            @RequestParam(required = false) Integer skip,
            @RequestParam(required = false) Integer limit
    ){
        var materias  = materiaService.findAllMateria(skip, limit);
        return new ApiResponse<>(HttpStatus.OK.value(), "list Modulos", materias);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Materia> getMateriaById(@PathVariable Long id){
        Materia materia=materiaService.getMateriaById(id);
        return ResponseEntity.ok(materia);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMateria(@PathVariable Long id) {
        materiaService.deleteMateria(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<MateriaResponse> updateMateria(@PathVariable Long id, @Valid @RequestBody MateriaDto materiaDto){
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Modulo Actualizado",
                new MateriaResponse(this.materiaService.actualizarMateria(id,materiaDto))
        );
    }

}
