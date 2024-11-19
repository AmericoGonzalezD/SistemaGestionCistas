package com.equipo3.gestionCitas.controllers;

import com.equipo3.gestionCitas.models.Cita;
import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.models.Psicologo;
import com.equipo3.gestionCitas.repositories.CitaRepository;
import com.equipo3.gestionCitas.repositories.ClienteRepository;
import com.equipo3.gestionCitas.repositories.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PsicologoRepository psicologoRepository;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> crearCita(
            @RequestParam Long idCliente,
            @RequestParam Long idPsicologo,
            @RequestParam String fecha,
            @RequestParam String hora,
            @RequestParam(defaultValue = "Pendiente") String estado) {

        // Validar existencia del cliente
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        // Validar existencia del psicólogo
        Psicologo psicologo = psicologoRepository.findById(idPsicologo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo no encontrado"));

        // Crear y guardar la nueva cita
        Cita nuevaCita = new Cita();
        nuevaCita.setCliente(cliente);
        nuevaCita.setPsicologo(psicologo);
        nuevaCita.setFecha(LocalDate.parse(fecha));
        nuevaCita.setHora(LocalTime.parse(hora));
        nuevaCita.setEstado(estado);

        citaRepository.save(nuevaCita);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cita creada correctamente.");
    }
    @CrossOrigin
    @DeleteMapping("/{idCita}")
    public ResponseEntity<String> eliminarCita(@PathVariable Long idCita) {
            if(!citaRepository.existsById(idCita)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cita no existe");
            }
            citaRepository.deleteById(idCita);
            return ResponseEntity.noContent().build();
    }

}
