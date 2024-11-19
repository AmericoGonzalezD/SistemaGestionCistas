package com.equipo3.gestionCitas.controllers;

import com.equipo3.gestionCitas.DTO.UsuarioDTO;
import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.models.Psicologo;
import com.equipo3.gestionCitas.models.Usuario;
import com.equipo3.gestionCitas.repositories.ClienteRepository;
import com.equipo3.gestionCitas.repositories.UsuarioRolRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/usuarios")
public class UsuarioRolController {
        @Autowired
        private UsuarioRolRepository usuarioRolRepository;

        @CrossOrigin
        @GetMapping("/clientes")
        public List<UsuarioDTO> obtenerUsuariosClientes() {
            List<Usuario> clientes = usuarioRolRepository.findByRol("cliente");
            return clientes.stream()
                    .map(this::convertirAUsuarioDTO)
                    .toList();
        }
    @CrossOrigin
    @GetMapping("/psicologos")
    public List<UsuarioDTO> obtenerUsuariosPsicologo() {
        List<Usuario> clientes = usuarioRolRepository.findByRol("psicologo");
        return clientes.stream()
                .map(this::convertirAUsuarioDTO)
                .toList();
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
