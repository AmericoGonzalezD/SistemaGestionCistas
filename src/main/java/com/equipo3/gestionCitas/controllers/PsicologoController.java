package com.equipo3.gestionCitas.controllers;

import com.equipo3.gestionCitas.DTO.CitaDTO;
import com.equipo3.gestionCitas.DTO.PsicologoDTO;
import com.equipo3.gestionCitas.models.Cita;
import com.equipo3.gestionCitas.models.Especialidad;
import com.equipo3.gestionCitas.models.Psicologo;
import com.equipo3.gestionCitas.models.PsicologoEspecialidad;
import com.equipo3.gestionCitas.repositories.EspecialidadRepository;
import com.equipo3.gestionCitas.repositories.PsicologoEspecialidadRepository;
import com.equipo3.gestionCitas.repositories.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/psicologo")
public class PsicologoController {

    @Autowired
    private PsicologoRepository psicologoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private PsicologoEspecialidadRepository psicologoEspecialidadRepository;

    @PostMapping("/{idPsicologo}/especialidad/{idEspecialidad}")
    public ResponseEntity<String> agregarEspecialidad(
            @PathVariable Long idPsicologo,
            @PathVariable Long idEspecialidad) {

        // Verificar existencia de psicólogo
        Psicologo psicologo = psicologoRepository.findById(idPsicologo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo no encontrado"));

        // Verificar existencia de especialidad
        Especialidad especialidad = especialidadRepository.findById(idEspecialidad)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad no encontrada"));

        // Crear relación
        PsicologoEspecialidad relacion = new PsicologoEspecialidad(idPsicologo, idEspecialidad);
        psicologoEspecialidadRepository.save(relacion);

        return ResponseEntity.status(HttpStatus.CREATED).body("Especialidad asignada correctamente.");
    }
    @CrossOrigin // Permite que este endpoint sea accesible desde servicios externos
    @GetMapping // Obtiene todos los psicólogos
    public List<PsicologoDTO> obtenerTodosLosPsicologos() {
        List<Psicologo> psicologos = psicologoRepository.findAll(); // Obtiene todos los psicólogos
        return psicologos.stream() // Convierte la lista de Psicólogo a PsicologoDTO
                .map(this::convertirPsicologoDTO) // Transforma cada Psicólogo en un PsicologoDTO
                .toList();
    }

    private PsicologoDTO convertirPsicologoDTO(Psicologo psicologo) {
        // Se accede al nombre del usuario asociado al psicólogo
        String nombrePsicologo = psicologo.getUsuario() != null ? psicologo.getUsuario().getNombre() : "Desconocido";
        return new PsicologoDTO(
                psicologo.getId(),
                psicologo.getCedulaProfesional(),
                nombrePsicologo // Incluir nombre del psicólogo
        );
    }

}


