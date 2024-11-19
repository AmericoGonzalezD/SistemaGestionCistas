package com.equipo3.gestionCitas.controllers;

import com.equipo3.gestionCitas.models.Cita;
import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.models.Psicologo;
import com.equipo3.gestionCitas.models.Usuario;
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
import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PsicologoRepository psicologoRepository;

    @CrossOrigin//puedo usar este endpoint por servicos externos (frontend)
    @GetMapping()//porque se quiere obtener todas
    public List<Cita> obtenerTodosLasCitas(){
        return citaRepository.findAll();
    }
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
    @CrossOrigin
    @PutMapping("/{idCita}")
    public ResponseEntity<String> actualizarCita(
            @PathVariable Long idCita,
            @RequestParam Long idPsicologo,
            @RequestParam String fecha,
            @RequestParam String hora) {

        // Validar existencia de la cita
        Cita citaExistente = citaRepository.findById(idCita)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        // Validar existencia del psicólogo
        Psicologo psicologo = psicologoRepository.findById(idPsicologo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo no encontrado"));

        // Actualizar los campos de la cita
        citaExistente.setPsicologo(psicologo);
        citaExistente.setFecha(LocalDate.parse(fecha));
        citaExistente.setHora(LocalTime.parse(hora));

        // Guardar la cita actualizada
        citaRepository.save(citaExistente);

        return ResponseEntity.status(HttpStatus.OK).body("Cita actualizada correctamente.");
    }
    @CrossOrigin
    @PutMapping("/{idCita}/estado")
    public ResponseEntity<String> actualizarEstadoCita(
            @PathVariable Long idCita,
            @RequestParam String estado) {

        // Validar existencia de la cita
        Cita citaExistente = citaRepository.findById(idCita)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        // Actualizar el estado de la cita
        citaExistente.setEstado(estado);

        // Guardar la cita con el nuevo estado
        citaRepository.save(citaExistente);

        return ResponseEntity.status(HttpStatus.OK).body("Estado de la cita actualizado correctamente.");
    }

}
