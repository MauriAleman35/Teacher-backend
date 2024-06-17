package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.CarreraDto;
import net_javaguides.teacher.Dto.MateriaDto;
import net_javaguides.teacher.entity.School.Carrera;
import net_javaguides.teacher.entity.School.Materia;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.University.CarreraResponse;
import net_javaguides.teacher.response.University.MateriaResponse;
import net_javaguides.teacher.services.school.CarreraService;
import net_javaguides.teacher.services.school.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrera")
public class CarreraController {
    @Autowired
    private CarreraService carreraService;

    @PostMapping("/addCarrera")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CarreraResponse> postCarrera(@Valid @RequestBody CarreraDto carreraDto){
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Carrera CREADO",
                new CarreraResponse(carreraService.carreraCreate(carreraDto))
        );

    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Page<Carrera>> getMethodName(
            @RequestParam(required = false) Integer skip,
            @RequestParam(required = false) Integer limit
    ){
        var carrera  = carreraService.findAllCarrera(skip, limit);
        return new ApiResponse<>(HttpStatus.OK.value(), "list Carreras", carrera);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable Long id){
        Carrera carrera=carreraService.getCarreraById(id);
        return ResponseEntity.ok(carrera);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCarrera(@PathVariable Long id) {
        carreraService.deleteCarrera(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CarreraResponse> updateCarrera(@PathVariable Long id, @Valid @RequestBody CarreraDto carreraDto){
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Carrera Actualizado",
                new CarreraResponse(this.carreraService.actualizarCarrera(id,carreraDto))
        );
    }


}
