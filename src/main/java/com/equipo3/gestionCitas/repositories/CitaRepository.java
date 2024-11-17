package com.equipo3.gestionCitas.repositories;

import com.equipo3.gestionCitas.models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    //se pueden declarar los metodos adicionales aqui si no lo incluye jpaRepository
}
