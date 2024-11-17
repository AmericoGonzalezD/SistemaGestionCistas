package com.equipo3.gestionCitas.controllers;

import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //indica que  es un controlador
@RequestMapping("/api/cliente")//indica la ruta que va a controlar mediante apiRest
public class ClienteController {
    @Autowired//hace evitar que secreen nuevos objetos cada vez que se ejute esta clase repositoria
    private ClienteRepository clienteRepository;// creo un objeto para usarlo

    @GetMapping("/api/cliente/all")//porque se quiere obtener todas
    public List<Cliente> obtenerTodosLosClientes(){
        return clienteRepository.findAll();
    }
}
