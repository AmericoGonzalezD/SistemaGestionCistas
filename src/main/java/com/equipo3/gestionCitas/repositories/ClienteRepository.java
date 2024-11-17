package com.equipo3.gestionCitas.repositories;

import com.equipo3.gestionCitas.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
