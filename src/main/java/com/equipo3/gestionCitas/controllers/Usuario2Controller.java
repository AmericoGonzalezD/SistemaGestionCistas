package com.equipo3.gestionCitas.controllers;


import com.equipo3.gestionCitas.DTO.UsuarioDTO;
import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.models.Psicologo;
import com.equipo3.gestionCitas.models.Usuario;
import com.equipo3.gestionCitas.repositories.ClienteRepository;
import com.equipo3.gestionCitas.repositories.PsicologoRepository;
import com.equipo3.gestionCitas.repositories.UsuarioRepository;
import com.equipo3.gestionCitas.repositories.UsuarioRolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        @PostMapping
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
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Usuario> borrarCliente(@PathVariable Long idUsuario){

        if(!usuarioRepository.existsById(idUsuario)){//compruebo si existe el cliente
            return ResponseEntity.notFound().build();//si no existe el cliente se devuelve una respuesta de notFound
        }
        usuarioRepository.deleteById(idUsuario);
        return ResponseEntity.noContent().build();//respuesta de que no hay contenido
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

    private UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
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
    }

