package com.equipo3.gestionCitas.controllers;


import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.models.Psicologo;
import com.equipo3.gestionCitas.models.Usuario;
import com.equipo3.gestionCitas.repositories.ClienteRepository;
import com.equipo3.gestionCitas.repositories.PsicologoRepository;
import com.equipo3.gestionCitas.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
    @RequestMapping("/usuarios")
    public class Usuario2Controller {

        @Autowired
        private UsuarioRepository usuarioRepository;

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
    @CrossOrigin//puedo usar este endpoint por servicos externos (frontend)
    @GetMapping("/{idUsuario}")//envia una variable mediante la uri
    public ResponseEntity<Usuario>  obtenerClientePorId(@PathVariable Long idUsuario){//tiene en cuenta que puede que no se encuentre el elemento con responseEntity
        Optional<Usuario> usuario= usuarioRepository.findById(idUsuario);
        return usuario.isPresent()?ResponseEntity.ok(usuario.get()):ResponseEntity.notFound().build();
    }
    }

