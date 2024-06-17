package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.user.DocenteDto;
import net_javaguides.teacher.entity.Users.Docente;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.DocenteResponse;

import net_javaguides.teacher.services.user.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/docente")
public class DocenteController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/addDocente")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<DocenteResponse> postMethodName(@Valid @RequestBody DocenteDto createdocenteDto) {

          return  new ApiResponse<>(
                    HttpStatus.CREATED.value(),
                    "USUARIO CREADO",
                    new DocenteResponse(usersService.DocenteCreate(createdocenteDto))
            );
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Page<Docente>> getMethodName(
            @RequestParam(required = false) Integer skip,
            @RequestParam(required = false) Integer limit
    ){
        var users  = usersService.findAllUserDocente(skip, limit);
        return new ApiResponse<>(HttpStatus.OK.value(), "list docentes", users);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable Long id){
        Docente docente=usersService.getDocenteById(id);
        return ResponseEntity.ok(docente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDocente(@PathVariable Long id) {
        usersService.deleteUserTeacher(id);
    }

}
