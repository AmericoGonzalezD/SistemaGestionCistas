package com.equipo3.gestionCitas.controllers;


import com.equipo3.gestionCitas.DTO.ActualizarUsuarioRequest;
import com.equipo3.gestionCitas.DTO.UsuarioClienteDTO;
import com.equipo3.gestionCitas.DTO.UsuarioDTO;
import com.equipo3.gestionCitas.models.*;
import com.equipo3.gestionCitas.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
    @RequestMapping("/usuarios")
    public class Usuario2Controller {

        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private UsuarioRolRepository usuarioRolRepository;

        @Autowired
        private PsicologoRepository psicologoRepository;

        @Autowired
        private ClienteRepository clienteRepository;
        @Autowired
        private CitaRepository citaRepository;

        @CrossOrigin
        @PostMapping("/editar")
        @Transactional
        public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario, @RequestParam(required = false) String cedulaProfesional,
                                                  @RequestParam(required = false) String estadoCivil,@RequestParam(required = false) String direccion) {
            // Guardar usuario en la tabla USUARIO
            Usuario usuarioGuardado = usuarioRepository.save(usuario);

            // Si el rol es PSICOLOGO, crear el registro en la tabla PSICOLOGO con la cédula profesional
            if (usuario.getRol().equalsIgnoreCase("psicologo") ) {
                if (cedulaProfesional == null || cedulaProfesional.isEmpty()) {
                    return ResponseEntity.badRequest().body("La cédula profesional es obligatoria para un psicólogo.");
                }

                // Crear el psicólogo asociado al usuario con la cédula profesional
                Psicologo psicologo = new Psicologo();
                psicologo.setUsuario(usuarioGuardado);
                psicologo.setCedulaProfesional(cedulaProfesional);

                // Guardar el psicólogo
                psicologoRepository.save(psicologo);
            }

            // Si el rol es CLIENTE, crear el registro en la tabla CLIENTE
            if (usuario.getRol().equalsIgnoreCase("cliente")) {
                Cliente cliente = new Cliente();
                cliente.setUsuario(usuarioGuardado);
                cliente.setEstadoCivil(estadoCivil);
                cliente.setDireccion(direccion);
                clienteRepository.save(cliente);
            }

            return ResponseEntity.ok(usuarioGuardado);
        }
    @CrossOrigin
    @GetMapping
    public List<UsuarioDTO> obtenerTodosLosClientes() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertirAUsuarioDTO) // Convierte cada Usuario a UsuarioDTO
                .toList();
    }


    @CrossOrigin
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obtenerClientePorId(@PathVariable Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        return usuario.map(value -> ResponseEntity.ok(convertirAUsuarioDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PutMapping("/{idUsuario}")
    public ResponseEntity<String> actualizarUsuario(
            @PathVariable Long idUsuario,
            @RequestBody UsuarioClienteDTO usuarioClienteDTO) {

        // Buscar el usuario por ID
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar si el correo ha cambiado y si ya existe
        if (!usuario.getCorreo().equals(usuarioClienteDTO.getCorreo())) {
            boolean correoExistente = usuarioRepository.findByCorreo(usuarioClienteDTO.getCorreo()).isPresent();
            if (correoExistente) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está en uso.");
            }
            usuario.setCorreo(usuarioClienteDTO.getCorreo());
        }

        // Actualizar otros campos
        usuario.setTelefono(usuarioClienteDTO.getTelefono());

        // Actualizar datos del cliente si aplica
        if (usuario.getCliente() != null) {
            Cliente cliente = usuario.getCliente();
            cliente.setDireccion(usuarioClienteDTO.getDireccion());
            cliente.setEstadoCivil(usuarioClienteDTO.getEstadoCivil());
        }

        // Guardar los cambios
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuario actualizado correctamente.");
    }

    @CrossOrigin
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Long idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOptional.get();

        // Eliminar las citas relacionadas con el usuario antes de eliminar el usuario
        if (usuario.getCitas() != null) {
            for (Cita cita : usuario.getCitas()) {
                citaRepository.delete(cita);  // Asegúrate de que tienes el repositorio de citas
            }
        }

        // Eliminar el psicologo y cliente si existen
        if (usuario.getPsicologo() != null) {
            psicologoRepository.delete(usuario.getPsicologo());
        }

        if (usuario.getCliente() != null) {
            clienteRepository.delete(usuario.getCliente());
        }

        // Eliminar el usuario
        usuarioRepository.delete(usuario);

        return ResponseEntity.noContent().build();
    }


    @CrossOrigin
    @PutMapping("/{idUsuario}/nombre")
    public ResponseEntity<Usuario> actualizarCliente(@PathVariable Long idUsuario, @RequestBody Usuario usuarioNombre){
        if(!usuarioRepository.existsById(idUsuario)){
            return ResponseEntity.notFound().build();//si no existe el usuario se devuelve la respuesta de notFound
        }
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        //usuario.setIdUsuario(idUsuario);//al usuario que me dieron le asigno el id que me pasan debido a que esos datos no tiene un id dado
        usuario.setNombre(usuarioNombre.getNombre());
        Usuario clienteActualizado = usuarioRepository.save(usuario);//actualiza el valor
        return ResponseEntity.ok(clienteActualizado);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String correo = loginRequest.getCorreo();
        String password = loginRequest.getPassword();

        // Buscar usuario por correo
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado.");
        }
        Usuario usuario = usuarioOpt.get();

        // Validar la contraseña (asegurar que la contraseña está almacenada correctamente)
        if (!usuario.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta.");
        }
        // Devolver la información del rol
        Map<String, String> response = new HashMap<>();
        response.put("rol", usuario.getRol());
        response.put("nombre", usuario.getNombre());
        response.put("idUsuario", String.valueOf(usuario.getIdUsuario()));
        return ResponseEntity.ok(response);
    }



    private UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        // Obtener datos adicionales si el usuario es cliente
        String direccion = null;
        String estadoCivil = null;
        if (usuario.getCliente() != null) {
            Cliente cliente = usuario.getCliente();
            direccion = cliente.getDireccion();
            estadoCivil = cliente.getEstadoCivil();
        }

        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getEdad(),
                usuario.getSexo(),
                usuario.getRol()
        );
    }
    private UsuarioClienteDTO convertirAUsuarioClienteDTO(Usuario usuario) {
        // Obtener datos adicionales si el usuario es cliente
        String direccion = null;
        String estadoCivil = null;
        if (usuario.getCliente() != null) {
            Cliente cliente = usuario.getCliente();
            direccion = cliente.getDireccion();
            estadoCivil = cliente.getEstadoCivil();
        }

        return new UsuarioClienteDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getEdad(),
                usuario.getSexo(),
                usuario.getRol(),
                direccion,
                estadoCivil
        );
    }
    }

