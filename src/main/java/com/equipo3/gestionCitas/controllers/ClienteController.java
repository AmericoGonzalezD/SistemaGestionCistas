package com.equipo3.gestionCitas.controllers;

import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController //indica que  es un controlador
@RequestMapping("/api/cliente")//indica la ruta que va a controlar mediante apiRest
public class ClienteController {
    @Autowired//hace evitar que secreen nuevos objetos cada vez que se ejute esta clase repositoria
    private ClienteRepository clienteRepository;// creo un objeto para usarlo

    @GetMapping("/all")//porque se quiere obtener todas
    public List<Cliente> obtenerTodosLosClientes(){
        return clienteRepository.findAll();
    }
    @GetMapping("/{idCliente}")//envia una variable mediante la uri
    public ResponseEntity<Cliente>  obtenerClientePorId(@PathVariable Long idCliente){//tiene en cuenta que puede que no se encuentre el elemento con responseEntity
       Optional<Cliente> cliente= clienteRepository.findById(idCliente);
       return cliente.isPresent()?ResponseEntity.ok(cliente.get()):ResponseEntity.notFound().build();
    }
}
