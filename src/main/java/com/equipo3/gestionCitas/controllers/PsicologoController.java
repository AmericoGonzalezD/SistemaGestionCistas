package com.equipo3.gestionCitas.controllers;

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
}


