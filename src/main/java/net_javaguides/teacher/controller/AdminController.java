package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.user.AdminDto;
import net_javaguides.teacher.entity.School.Modulo;
import net_javaguides.teacher.entity.Users.Admin;
import net_javaguides.teacher.repository.AdminRepository;
import net_javaguides.teacher.response.AdminResponse;
import net_javaguides.teacher.response.ApiResponse;

import net_javaguides.teacher.services.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/addAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin postMethodName(@Valid @RequestBody AdminDto createAdminDto) {

        var adminCi=adminRepository.findByCi(createAdminDto.getCi());
        this.usersService.createAdmin(createAdminDto);
        return adminCi.get();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Page<Admin>> getMethodName(
            @RequestParam(required = false) Integer skip,
            @RequestParam(required = false) Integer limit
    ){
        var users  = usersService.findAllUserAdmin(skip, limit);
        return new ApiResponse<>(HttpStatus.OK.value(), "list docentes", users);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id){
        Admin admin=usersService.getAdminById(id);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdmin(@PathVariable Long id) {
        usersService.deleteUserAdmin(id);
    }



}
