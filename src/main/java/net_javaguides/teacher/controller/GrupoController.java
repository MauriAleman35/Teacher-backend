package net_javaguides.teacher.controller;
import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.GrupoDto;
import net_javaguides.teacher.Dto.ModuloDto;
import net_javaguides.teacher.entity.School.Grupo;
import net_javaguides.teacher.entity.School.Modulo;
import net_javaguides.teacher.error.exception.NotFoundException;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.University.GrupoResponse;
import net_javaguides.teacher.response.University.ModuloResponse;
import net_javaguides.teacher.services.school.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "grupo")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;


    @PostMapping("/addGrupo")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<GrupoResponse> addGrupoPost(@Valid @RequestBody GrupoDto creategrupoDto) {
          return new ApiResponse<>(
                       HttpStatus.CREATED.value(),
                         "Grupo CREADO",
                         new GrupoResponse(grupoService.grupoCreate(creategrupoDto))
                );



    }
    @GetMapping()
    public List<Grupo> getGrupoAll(){
       return this.grupoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> getGrupoById(@PathVariable Long id){
        Grupo grupo=grupoService.findGrupoId(id);
        return ResponseEntity.ok(grupo);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGrupo(@PathVariable Long id) {
        grupoService.deleteGrupo(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<GrupoResponse> updateGrupo(@PathVariable Long id, @Valid @RequestBody GrupoDto grupoDto){
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Grupo Actualizado",
                new GrupoResponse(this.grupoService.actualizarGrupo(id,grupoDto))
        );
    }


}
