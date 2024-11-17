package com.equipo3.gestionCitas.controllers;

import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //indica que  es un controlador
@RequestMapping("/api/cliente")//indica la ruta que va a controlar mediante apiRest
public class ClienteController {
    @Autowired//hace evitar que secreen nuevos objetos cada vez que se ejute esta clase repositoria
    private ClienteRepository clienteRepository;// creo un objeto para usarlo

    @CrossOrigin//puedo usar este endpoint por servicos externos (frontend)
    @GetMapping("/all")//porque se quiere obtener todas
    public List<Cliente> obtenerTodosLosClientes(){
        return clienteRepository.findAll();
    }
    @CrossOrigin//puedo usar este endpoint por servicos externos (frontend)
    @GetMapping("/{idCliente}")//envia una variable mediante la uri
    public ResponseEntity<Cliente>  obtenerClientePorId(@PathVariable Long idCliente){//tiene en cuenta que puede que no se encuentre el elemento con responseEntity
       Optional<Cliente> cliente= clienteRepository.findById(idCliente);
       return cliente.isPresent()?ResponseEntity.ok(cliente.get()):ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){//los dados los reccibe en el cuerpo de la peticion con @RequestBody
        Cliente clienteGuardado = clienteRepository.save(cliente);//lo que resivo lo guardo y le paso la entidad recibida
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);//respuesta
    }

    @CrossOrigin
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Cliente> borrarCliente(@PathVariable Long idCliente){
        if(!clienteRepository.existsById(idCliente)){//compruebo si existe el cliente
            return ResponseEntity.notFound().build();//si no existe el cliente se devuelve una respuesta de notFound
        }
        clienteRepository.deleteById(idCliente);
        return ResponseEntity.noContent().build();//respuesta de que no hay contenido
    }
    @CrossOrigin
    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long idCliente, @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(idCliente)){
            return ResponseEntity.notFound().build();//si no existe el cliente se devuelve la respuesta de notFound
        }
        cliente.setId(idCliente);//al cliente que me dieron le asigno el id que me pasan debido a que esos datos no tiene un id dado
        Cliente clienteGuardado = clienteRepository.save(cliente);//actualiza el valor
        return ResponseEntity.ok(clienteGuardado);
    }
}
