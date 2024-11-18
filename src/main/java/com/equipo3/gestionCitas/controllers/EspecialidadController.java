package com.equipo3.gestionCitas.controllers;

import com.equipo3.gestionCitas.models.Especialidad;
import com.equipo3.gestionCitas.repositories.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @CrossOrigin
    @GetMapping
    public List<Especialidad> obtenerEspecialidades(){
        return especialidadRepository.findAll();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Especialidad> guardarEspecialidad(@RequestBody Especialidad especialidad) {
        Especialidad especialidadGuardada=especialidadRepository.save(especialidad);
        return ResponseEntity.ok(especialidadGuardada);
    }

}
