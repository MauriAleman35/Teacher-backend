package net_javaguides.teacher.services.school;

import lombok.AllArgsConstructor;
import net_javaguides.teacher.Dto.SolicitudDto;
import net_javaguides.teacher.entity.School.SolicitudAsistencia;
import net_javaguides.teacher.entity.Users.Docente;
import net_javaguides.teacher.error.exception.NotFoundException;
import net_javaguides.teacher.repository.DocenteRepository;
import net_javaguides.teacher.repository.School.SolicitudLicenciaRepository;
import net_javaguides.teacher.services.Interfaces.SolicitudAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SolicitudService implements SolicitudAsistenciaService {
    @Autowired
    private SolicitudLicenciaRepository solicitudLicenciaRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    public SolicitudAsistencia createSolicitudAsistencia(SolicitudDto solicitudAsistenciaDto) {
        var docenteOptional = docenteRepository.findById(solicitudAsistenciaDto.getIdDocente());
        if (!docenteOptional.isPresent()) {
            throw new NotFoundException("docente not found");
        }


        LocalDate fechaSolicitud = LocalDate.now();  // Establecer la fecha de solicitud a la fecha actual

        // Crear una nueva solicitud con el estado inicial `false`
        SolicitudAsistencia solicitudAsistencia = new SolicitudAsistencia(
                solicitudAsistenciaDto.getFechaInicio(),
                solicitudAsistenciaDto.getFechaFin(),
                solicitudAsistenciaDto.getMotivo(),
                false,  // Estado inicial falso
                fechaSolicitud,
                docenteOptional.get()
        );

        return solicitudLicenciaRepository.save(solicitudAsistencia);
    }

    @Override
    public SolicitudAsistencia updateSolicitudAsistencia(Long id, SolicitudDto solicitudAsistenciaDto) {
        Optional<SolicitudAsistencia> solicitudAsistenciaOptional = solicitudLicenciaRepository.findById(id);
        if (!solicitudAsistenciaOptional.isPresent()) {
            throw new NotFoundException("SolicitudAsistencia not found");
        }
        LocalDate fechaSolicitud = LocalDate.now();
        SolicitudAsistencia solicitudAsistencia = solicitudAsistenciaOptional.get();
        solicitudAsistencia.setFechaInicio(solicitudAsistenciaDto.getFechaInicio());
        solicitudAsistencia.setFechaFin(solicitudAsistenciaDto.getFechaFin());
        solicitudAsistencia.setMotivo(solicitudAsistenciaDto.getMotivo());
        // Mantener el estado actual (no se cambia aquí)
        solicitudAsistencia.setFechaSolicitud(fechaSolicitud);

        Optional<Docente> docenteOptional = docenteRepository.findById(solicitudAsistenciaDto.getIdDocente());
        if (docenteOptional.isPresent()) {
            solicitudAsistencia.setDocente(docenteOptional.get());
        }

        return solicitudLicenciaRepository.save(solicitudAsistencia);


    }

    @Override
    public SolicitudAsistencia updateEstadoSolicitudAsistencia(Long id, boolean estado) {
        Optional<SolicitudAsistencia> solicitudAsistenciaOptional = solicitudLicenciaRepository.findById(id);
        if (!solicitudAsistenciaOptional.isPresent()) {
            throw new NotFoundException("SolicitudAsistencia not found");
        }

        SolicitudAsistencia solicitudAsistencia = solicitudAsistenciaOptional.get();
        solicitudAsistencia.setEstado(estado);

        return solicitudLicenciaRepository.save(solicitudAsistencia);
    }

    @Override
    public void deleteSolicitudAsistencia(Long id) {
        if (!solicitudLicenciaRepository.existsById(id)) {
            throw new NotFoundException("SolicitudAsistencia not found");
        }
        solicitudLicenciaRepository.deleteById(id);
    }

    @Override
    public SolicitudAsistencia getSolicitudAsistenciaById(Long id) {
        return solicitudLicenciaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SolicitudAsistencia not found"));
    }

    @Override
    public List<SolicitudAsistencia> getAllSolicitudesAsistencia() {
        return solicitudLicenciaRepository.findAll();
    }
}
