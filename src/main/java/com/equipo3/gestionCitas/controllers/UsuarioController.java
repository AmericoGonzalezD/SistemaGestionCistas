package com.equipo3.gestionCitas.controllers;
/*
import com.equipo3.gestionCitas.models.Usuario;
import com.equipo3.gestionCitas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //indica que  es un controlador
@RequestMapping("/api/usuario")//indica la ruta que va a controlar mediante apiRest
public class UsuarioController {
    @Autowired//hace evitar que secreen nuevos objetos cada vez que se ejute esta clase repositoria
    private UsuarioRepository usuarioRepository;// creo un objeto para usarlo

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
    @PostMapping
    public ResponseEntity<Usuario> crearCliente(@RequestBody Usuario usuario){//los dados los reccibe en el cuerpo de la peticion con @RequestBody
        Usuario usuarioGuardado = usuarioRepository.save(usuario);//lo que resivo lo guardo y le paso la entidad recibida
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);//respuesta
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
    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> actualizarCliente(@PathVariable Long idUsuario, @RequestBody Usuario usuario){
        if(!usuarioRepository.existsById(idUsuario)){
            return ResponseEntity.notFound().build();//si no existe el usuario se devuelve la respuesta de notFound
        }
        usuario.setIdUsuario(idUsuario);//al usuario que me dieron le asigno el id que me pasan debido a que esos datos no tiene un id dado
        Usuario clienteGuardado = usuarioRepository.save(usuario);//actualiza el valor
        return ResponseEntity.ok(clienteGuardado);
    }
}*/
