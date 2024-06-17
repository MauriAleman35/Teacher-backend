package net_javaguides.teacher.services.school;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.ModuloDto;
import net_javaguides.teacher.entity.School.Modulo;
import net_javaguides.teacher.entity.Users.Docente;
import net_javaguides.teacher.error.exception.BadRequestException;
import net_javaguides.teacher.error.exception.NotFoundException;
import net_javaguides.teacher.repository.School.FacultadRepository;
import net_javaguides.teacher.repository.School.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModuloService {
    @Autowired
    ModuloRepository moduloRepository;

    @Autowired
    FacultadRepository facultadRepository;

    public Modulo moduloCreate(ModuloDto moduloDto){
        /*
        *  var findModulo=moduloRepository.findByNro(moduloDto.getNumero());
        if(findModulo.isPresent()){
            throw new BadRequestException(
                    "El Modulo numero"+ findModulo.get().getNumero()+"se encuentra en el sistema"
            );
        }

        *ss
        *
        * */
            var facultad=facultadRepository.getReferenceById(moduloDto.getIdFacultad());
            var modulocreate=new Modulo(
                    moduloDto.getNumero(),
                    moduloDto.getDireccion(),
                    facultad

            );
            moduloRepository.save(modulocreate);
            return modulocreate;
    }

    public Page<Modulo> findAllModulo(int skip, int limit){
        Pageable pageable= PageRequest.of(skip,limit);

        var modulos=this.moduloRepository.findAll(pageable);
        return modulos;
    }

    public Modulo getModuloById(Long id){
        return moduloRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe"));
    }

    @Transactional
    public void deleteModulo(Long id) {
        var findModuloId = this.moduloRepository.findById(id);
        if(findModuloId.isPresent()) {
            this.moduloRepository.deleteById(id);
        } else {
            throw new NotFoundException("Modulo with id " + id + " not found");
        }
    }
    public Modulo findModuloId(Long id){
        var findModulo=this.moduloRepository.findById(id);
        if(!findModulo.isPresent()){
            throw new NotFoundException(
                    "matter id " +id +" not found"
            );
        }
        return findModulo.get();
    }
    //Matter
    public Modulo actualizarModulo(Long id, ModuloDto moduloDto){
        var findModuloId=this.findModuloId(id);
        var ModuloUpdate=findModuloId;
        ModuloUpdate.setNumero(moduloDto.getNumero());
        ModuloUpdate.setDireccion(moduloDto.getDireccion());
        return this.moduloRepository.save(ModuloUpdate);
    }

}
