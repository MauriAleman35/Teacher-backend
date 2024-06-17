package net_javaguides.teacher.services.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import net_javaguides.teacher.Dto.user.AdminDto;
import net_javaguides.teacher.Dto.user.DocenteDto;
import net_javaguides.teacher.entity.Users.Admin;
import net_javaguides.teacher.entity.Users.Docente;
import net_javaguides.teacher.entity.Users.Rol;
import net_javaguides.teacher.entity.Users.User;
import net_javaguides.teacher.error.exception.BadRequestException;
import net_javaguides.teacher.error.exception.NotFoundException;
import net_javaguides.teacher.repository.AdminRepository;
import net_javaguides.teacher.repository.DocenteRepository;
import net_javaguides.teacher.repository.RolRepository;
import net_javaguides.teacher.repository.School.GrupoRepository;
import net_javaguides.teacher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UsersService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DocenteRepository docenteRepository;
    @Autowired
    private GrupoRepository grupoRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;





    public User createUser(String codigo, String contraseña,Rol rolCreate) {

        User user = new User(codigo, contraseña,rolCreate);
        return userRepository.save(user);
    }
// bearer
    public Docente DocenteCreate(DocenteDto docenteDto){
        var findUser=this.docenteRepository.findByEmail(docenteDto.getEmail());
        if(findUser.isPresent()){
            throw new BadRequestException(
                    "El usuario con Email"+ findUser.get().getEmail()+"se encuentra en el sistema"
            );
        }

        findUser=this.docenteRepository.findByPhone(docenteDto.getPhone());
        if(findUser.isPresent()){
            throw new BadRequestException(
                    "El numero +591 "+findUser.get().getPhone()+" ya se encuentra en uso en el sistema"
            );
        }

        try {
            
            String codigo = docenteDto.getCi();
            String contraseña = docenteDto.getCi() + "."+docenteDto.getName().substring(0,1).toUpperCase();
            String encoderPassword=new BCryptPasswordEncoder().encode(contraseña);

            var rol=rolRepository.getReferenceById(2L);

            User user=createUser(codigo,encoderPassword,rol);
            var docentCreate= new Docente(
                    docenteDto.getName().toLowerCase(),
                    docenteDto.getCi(),
                    docenteDto.getEmail(),
                    docenteDto.getPhone(),
                    user

            );
            docenteRepository.save(docentCreate);
            return docentCreate;
        } catch (IllegalArgumentException e){
            throw new NotFoundException("Rol not found");

        }


    }


    public Admin createAdmin(AdminDto adminDto) {
        var findUser=this.adminRepository.findByEmail(adminDto.getEmail());
        if(findUser.isPresent()){
            throw new BadRequestException(
                    "El usuario con Email"+ findUser.get().getEmail()+"se encuentra en el sistema"
            );
        }

        findUser=this.adminRepository.findByPhone(adminDto.getPhone());
        if(findUser.isPresent()){
            throw new BadRequestException(
                    "El numero +591 "+findUser.get().getPhone()+" ya se encuentra en uso en el sistema"
            );
        }

        try {

            String codigo = adminDto.getCi();
            String contraseña = adminDto.getCi() + "."+adminDto.getName().substring(0,1).toUpperCase();
            String encoderPassword=new BCryptPasswordEncoder().encode(contraseña);
            var rol=rolRepository.getReferenceById(1L);

            User user=createUser(codigo,encoderPassword,rol);
            var AdminCreate= new Admin(
                    adminDto.getName().toLowerCase(),
                    adminDto.getCi(),
                    adminDto.getEmail(),
                    adminDto.getPhone(),
                    user

            );
            adminRepository.save(AdminCreate);
            return AdminCreate;
        } catch (IllegalArgumentException e){
            throw new NotFoundException("Rol not found");

        }

    }


    public Docente getDocenteById(Long id){
        return docenteRepository.findById(id).orElseThrow(()-> new NotFoundException("No existe el Docente seleccionado"));
    }

    public Admin getAdminById(Long id){
        return adminRepository.findById(id).orElseThrow(()-> new NotFoundException("No existe el Admin seleccionado"));
    }

    public Page<Docente> findAllUserDocente(int skip,int limit){
        Pageable pageable= PageRequest.of(skip,limit);

        var users=this.docenteRepository.findAll(pageable);
        return users;
    }
    public Page<Admin> findAllUserAdmin(int skip,int limit){
        Pageable pageable= PageRequest.of(skip,limit);

        var users=this.adminRepository.findAll(pageable);
        return users;
    }

    @Transactional
    public void deleteUserTeacher(Long id) {
        var findTeacher = this.docenteRepository.findById(id);
        var findUser=this.userRepository.findByCodigo(findTeacher.get().getCi());
        if(findTeacher.isPresent() && findUser.isPresent()) {
            this.docenteRepository.deleteById(id);
            this.userRepository.deleteById(findUser.get().getId());
        } else {
            throw new NotFoundException("User with id " + id + " not found");
        }
    }

    @Transactional
    public void deleteUserAdmin(Long id) {
        var findAdmin = this.adminRepository.findById(id);
        var findUser=this.userRepository.findByCodigo(findAdmin.get().getCi());
        if(findAdmin.isPresent() && findUser.isPresent()) {
            this.adminRepository.deleteById(id);
            this.userRepository.deleteById(findUser.get().getId());
        } else {
            throw new NotFoundException("User with id " + id + " not found");
        }
    }










}
