package net_javaguides.teacher.services.school;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.AulaDto;
import net_javaguides.teacher.entity.School.Aula;
import net_javaguides.teacher.error.exception.BadRequestException;
import net_javaguides.teacher.error.exception.NotFoundException;
import net_javaguides.teacher.repository.School.AulaRepository;
import net_javaguides.teacher.repository.School.ModuloRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class AulaService {
    private AulaRepository aulaRepository;

    private ModuloRepository moduloRepository;


    public Aula aulaCreate(AulaDto aulaDto){

        var modulo=moduloRepository.findById(aulaDto.getIdModulo());
        if(!modulo.isPresent()){
            throw new BadRequestException(
                    "El modulo del Aula no se encuentra"
            );
        }



        var nroAula=aulaRepository.findByNumero(aulaDto.getNumero());
        if(nroAula.isPresent()){
            throw new BadRequestException(
                    "El numero del Aula ya existe"
            );
        }
        var aulaCreate= new Aula(
                aulaDto.getNumero(),
                aulaDto.getCapacidad(),
                modulo.get()
        );
        return  aulaRepository.save(aulaCreate);

    }

    @Transactional
    public List<Aula> findAll(){
        return  aulaRepository.findAll();
    }

    public Aula findAulaId(Long id){
        var findAula=this.aulaRepository.findById(id);
        if(!findAula.isPresent()){
            throw new NotFoundException(
                    "Aula id " +id +" not found"
            );
        }
        return findAula.get();
    }

    @Transactional
    public void deleteAula(Long id) {
        if(this.aulaRepository.existsById(id)) {
            this.aulaRepository.deleteById(id);
        } else {
            throw new NotFoundException("Aula with id " + id + " not found");
        }
    }

    public Aula actualizarAula(Long id, AulaDto aulaDto){
        var findAula=aulaRepository.findById(id);
        if (!findAula.isPresent()){
            throw  new BadRequestException("No existe el Aula");
        }

        var findAulaId=this.findAulaId(id);

        var moduloFind=moduloRepository.findById(aulaDto.getIdModulo());

        var aulaUpdate=findAulaId;
        aulaUpdate.setNumero(aulaDto.getNumero());
        aulaUpdate.setCapacidad(aulaDto.getCapacidad());
        aulaUpdate.setModulo(moduloFind.get());

        return this.aulaRepository.save(aulaUpdate);
    }

}
