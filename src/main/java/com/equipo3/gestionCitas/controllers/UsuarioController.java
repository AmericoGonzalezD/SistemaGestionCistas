package com.equipo3.gestionCitas.controllers;

import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.models.Usuario;
import com.equipo3.gestionCitas.repositories.ClienteRepository;
import com.equipo3.gestionCitas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController //indica que  es un controlador
@RequestMapping("/api/usuario")//indica la ruta que va a controlar mediante apiRest
public class UsuarioController {
    @Autowired//hace evitar que secreen nuevos objetos cada vez que se ejute esta clase repositoria
    private UsuarioRepository usuarioRepository;// creo un objeto para usarlo

    @Autowired//hace evitar que secreen nuevos objetos cada vez que se ejute esta clase repositoria
    private ClienteRepository clienteRepository;// creo un objeto para usarlo

    @CrossOrigin//puedo usar este endpoint por servicos externos (frontend)
    @GetMapping("/all")//porque se quiere obtener todas
    public List<Usuario> obtenerTodosLosClientes(){
        return usuarioRepository.findAll();
    }
    @CrossOrigin//puedo usar este endpoint por servicos externos (frontend)
    @GetMapping("/{idUsuario}")//envia una variable mediante la uri
    public ResponseEntity<Usuario>  obtenerClientePorId(@PathVariable Long idUsuario){//tiene en cuenta que puede que no se encuentre el elemento con responseEntity
       Optional<Usuario> usuario= usuarioRepository.findById(idUsuario);
       return usuario.isPresent()?ResponseEntity.ok(usuario.get()):ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Object> crearCliente(@RequestBody Usuario usuario) {
        // Verificar si ya existe un usuario con el mismo correo
        Optional<Usuario> existingUser = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (existingUser.isPresent()) {
            // Si ya existe un usuario con ese correo, retornar un error con estado 409 (Conflict)
            // y enviar un mensaje personalizado al frontend
            Map<String, String> response = new HashMap<>();
            response.put("error", "Correo ya existente en el sistema");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        Cliente cliente = new Cliente();
        // Si no existe, guardar el nuevo usuario
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }

    @DeleteMapping("/usuarios/{idUsuario}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin
    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> actualizarCliente(@PathVariable Long idUsuario, @RequestBody Usuario usuario){
        if(!usuarioRepository.existsById(idUsuario)){
            return ResponseEntity.notFound().build();//si no existe el usuario se devuelve la respuesta de notFound
        }
        usuario.setIdUsuario(idUsuario);//al usuario que me dieron le asigno el id que me pasan debido a que esos datos no tiene un id dado
        Usuario clienteGuardado = usuarioRepository.save(usuario);//actualiza el valor
        return ResponseEntity.ok(clienteGuardado);
    }
}
