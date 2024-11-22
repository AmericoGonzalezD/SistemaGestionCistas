package com.equipo3.gestionCitas.controllers;

import com.equipo3.gestionCitas.DTO.CitaDTO;
import com.equipo3.gestionCitas.DTO.RespuestasCitasDTO;
import com.equipo3.gestionCitas.models.Cita;
import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.models.Psicologo;
import com.equipo3.gestionCitas.models.Usuario;
import com.equipo3.gestionCitas.repositories.CitaRepository;
import com.equipo3.gestionCitas.repositories.ClienteRepository;
import com.equipo3.gestionCitas.repositories.PsicologoRepository;
import com.equipo3.gestionCitas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PsicologoRepository psicologoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @CrossOrigin // Permite que este endpoint sea accesible desde servicios externos
    @GetMapping // Obtiene todas las citas
    public List<CitaDTO> obtenerTodosLasCitas() {
        List<Cita> citas = citaRepository.findAll(); // Obtiene todas las citas
        return citas.stream() // Convierte la lista de Cita a CitaDTO
                .map(this::convertirACitaDTO) // Transforma cada Cita en un CitaDTO
                .toList();
    }

    private CitaDTO convertirACitaDTO(Cita cita) {
        return new CitaDTO(
                cita.getIdCita(),
                cita.getCliente().getUsuario().getNombre(),
                cita.getPsicologo().getUsuario().getNombre(),
                cita.getFecha(),
                cita.getHora(),
                cita.getEstado()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtenerCitaPorId(@PathVariable Long id) {
        Optional<Cita> cita = citaRepository.findById(id);
        if (cita.isPresent()) {
            Cita entidad = cita.get();
            CitaDTO dto = new CitaDTO(
                    entidad.getIdCita(),
                    entidad.getCliente().getUsuario().getNombre(),
                    entidad.getPsicologo().getUsuario().getNombre(),
                    entidad.getFecha(),
                    entidad.getHora(),
                    entidad.getEstado()
            );
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cita no existe");
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
            @RequestParam String hora,
            @RequestParam String estado) {  // Agregado el parámetro de estado

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
        citaExistente.setEstado(estado);  // Actualización del estado de la cita

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

              // Obtener citas filtradas por el usuario autenticado
        @CrossOrigin
        @GetMapping("/mis-citas")
        public ResponseEntity<List<CitaDTO>> obtenerCitasPorUsuario(@RequestParam String correo) {
            // Buscar al usuario por correo
            Optional<Cliente> clienteOpt = clienteRepository.findByUsuarioCorreo(correo);
            Optional<Psicologo> psicologoOpt = psicologoRepository.findByUsuarioCorreo(correo);

            List<Cita> citas;

            if (clienteOpt.isPresent()) {
                // Si es un cliente, obtener sus citas
                Cliente cliente = clienteOpt.get();
                citas = citaRepository.findByCliente(cliente);
            } else if (psicologoOpt.isPresent()) {
                // Si es un psicólogo, obtener sus citas
                Psicologo psicologo = psicologoOpt.get();
                citas = citaRepository.findByPsicologo(psicologo);
            } else {
                // Si el usuario no es cliente ni psicólogo, devolver un error
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            // Convertir las citas a DTO
            List<CitaDTO> citaDTOs = citas.stream()
                    .map(this::convertirACitaDTO)
                    .toList();

            return ResponseEntity.ok(citaDTOs);
        }
    @CrossOrigin
    @GetMapping("/cliente") // Obtiene todas las citas del usuario que ha iniciado sesión
    public List<CitaDTO> obtenerCitasPorUsuario(@RequestParam Long idCliente) {
        // Busca al cliente por su ID
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        // Obtiene las citas del cliente
        List<Cita> citas = citaRepository.findByCliente(cliente);

        // Convierte las citas a DTO
        return citas.stream()
                .map(this::convertirACitaDTO)
                .toList();
    }
    /*
    @CrossOrigin
    @GetMapping("/psicologo") // Obtiene todas las citas del psicólogo que ha iniciado sesión
    public List<CitaDTO> obtenerCitasPorPsicologo(@RequestParam Long idPsicologo) {
        // Busca al psicólogo por su ID
        Psicologo psicologo = psicologoRepository.findById(idPsicologo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo no encontrado"));

        // Obtiene las citas del psicólogo
        List<Cita> citas = citaRepository.findByPsicologo(psicologo);

        // Convierte las citas a DTO
        return citas.stream()
                .map(this::convertirACitaDTO)
                .toList();
    }*/
    @CrossOrigin
    @GetMapping("/psicologo")
    public RespuestasCitasDTO obtenerCitasPorPsicologo(@RequestParam Long idPsicologo) {
        // Busca al psicólogo por su ID
        Psicologo psicologo = psicologoRepository.findById(idPsicologo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo no encontrado"));

        // Obtiene las citas del psicólogo
        List<Cita> citas = citaRepository.findByPsicologo(psicologo);

        // Convierte las citas a DTO
        List<CitaDTO> citaDTOs = citas.stream()
                .map(this::convertirACitaDTO)
                .toList();

        // Devuelve un DTO con las citas y el idPsicologo
        return new RespuestasCitasDTO(idPsicologo, citaDTOs);
    }




    // Métodos existentes...



}
