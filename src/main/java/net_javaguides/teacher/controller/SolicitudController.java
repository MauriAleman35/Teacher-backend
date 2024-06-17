package net_javaguides.teacher.controller;

import jakarta.validation.Valid;
import net_javaguides.teacher.Dto.SolicitudDto;
import net_javaguides.teacher.entity.School.Horario;
import net_javaguides.teacher.entity.School.SolicitudAsistencia;
import net_javaguides.teacher.response.ApiResponse;
import net_javaguides.teacher.response.University.GrupoResponse;
import net_javaguides.teacher.response.University.SolicitudResponse;
import net_javaguides.teacher.services.Interfaces.SolicitudAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitud")
public class SolicitudController {
    @Autowired
    private SolicitudAsistenciaService solicitudAsistenciaService;

    @PostMapping("/enviar")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SolicitudResponse> createSolicitudAsistencia(@Valid @RequestBody SolicitudDto solicitudAsistenciaDto) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Solicitud CREADO",
                new SolicitudResponse(solicitudAsistenciaService.createSolicitudAsistencia(solicitudAsistenciaDto))
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<SolicitudResponse> updateSolicitudAsistencia(
            @PathVariable("id") Long id, @Valid
            @RequestBody SolicitudDto solicitudAsistenciaDto) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Solcitud Actualizado",
                new SolicitudResponse(this.solicitudAsistenciaService.updateSolicitudAsistencia(id,solicitudAsistenciaDto))
        );
    }

    @PutMapping("/{id}/estado")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<SolicitudResponse> updateEstadoSolicitudAsistencia(
            @PathVariable("id") Long id,
            @RequestParam("estado") boolean estado) {
        SolicitudAsistencia solicitudActualizada = solicitudAsistenciaService.updateEstadoSolicitudAsistencia(id, estado);
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Grupo Actualizado",
                new SolicitudResponse(solicitudActualizada)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitudAsistencia(@PathVariable("id") Long id) {
        solicitudAsistenciaService.deleteSolicitudAsistencia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudAsistencia> getSolicitudAsistenciaById(@PathVariable("id") Long id) {
        SolicitudAsistencia solicitudAsistencia = solicitudAsistenciaService.getSolicitudAsistenciaById(id);
        return ResponseEntity.ok(solicitudAsistencia);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<SolicitudAsistencia> getSolicitudAll(){
        return this.solicitudAsistenciaService.getAllSolicitudesAsistencia();
    }
}
