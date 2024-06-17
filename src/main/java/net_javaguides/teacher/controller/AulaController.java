package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.AulaDto;
import net_javaguides.teacher.Dto.MateriaDto;
import net_javaguides.teacher.entity.School.Aula;
import net_javaguides.teacher.entity.School.Materia;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.University.AulaResponse;
import net_javaguides.teacher.response.University.MateriaResponse;
import net_javaguides.teacher.services.school.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aula")
public class AulaController {
    @Autowired
    private AulaService aulaService;

    @PostMapping("/addAula")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AulaResponse> postAula(@Valid @RequestBody AulaDto aulaDto){
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Aula Creada",
                new AulaResponse(aulaService.aulaCreate(aulaDto))
        );
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Aula> getMethodName(){
        var aulas = aulaService.findAll();
        return aulas;

    }
    @GetMapping("/{id}")
    public ResponseEntity<Aula> getAulaById(@PathVariable Long id){
        Aula aula=aulaService.findAulaId(id);
        return ResponseEntity.ok(aula);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAula(@PathVariable Long id) {
        aulaService.deleteAula(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<AulaResponse> updateAula(@PathVariable Long id, @Valid @RequestBody AulaDto aulaDto){
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Modulo Actualizado",
                new AulaResponse(this.aulaService.actualizarAula(id,aulaDto))
        );
    }

}
