package net_javaguides.teacher.controller;

import jakarta.validation.Valid;

import net_javaguides.teacher.Dto.user.RolDto;
import net_javaguides.teacher.entity.Users.Rol;

import net_javaguides.teacher.services.user.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping("/addRol")

    public ResponseEntity<Rol> postRol(@Valid @RequestBody RolDto createrolDto) {
        Rol rol=rolService.rolCreate(createrolDto);
        return  new ResponseEntity<>(rol,HttpStatus.CREATED);
    }
}
