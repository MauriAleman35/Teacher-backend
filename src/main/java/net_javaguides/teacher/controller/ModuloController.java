package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.ModuloDto;
import net_javaguides.teacher.entity.School.Modulo;
import net_javaguides.teacher.entity.Users.Admin;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.University.GrupoResponse;
import net_javaguides.teacher.response.University.ModuloResponse;
import net_javaguides.teacher.services.school.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "modulo")
public class ModuloController {
    @Autowired
    ModuloService moduloService;

    @PostMapping("/addModulo")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ModuloResponse> postCreateModulo(@Valid @RequestBody ModuloDto moduloDto){
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Modulo Creado",
                new ModuloResponse(moduloService.moduloCreate(moduloDto))
        );
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Page<Modulo>> getMethodName(
            @RequestParam(required = false) Integer skip,
            @RequestParam(required = false) Integer limit
    ){
        var modulos  = moduloService.findAllModulo(skip, limit);
        return new ApiResponse<>(HttpStatus.OK.value(), "list Modulos", modulos);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Modulo> getModuloById(@PathVariable Long id){
        Modulo modulo=moduloService.getModuloById(id);
        return ResponseEntity.ok(modulo);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDocente(@PathVariable Long id) {
        moduloService.deleteModulo(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ModuloResponse> updateModulo(@PathVariable Long id, @Valid @RequestBody ModuloDto moduloDto){
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Modulo Actualizado",
                new ModuloResponse(this.moduloService.actualizarModulo(id,moduloDto))
        );
    }

}
