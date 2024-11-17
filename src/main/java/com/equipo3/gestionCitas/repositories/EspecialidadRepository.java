package com.equipo3.gestionCitas.repositories;

import com.equipo3.gestionCitas.models.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    //se pueden declarar los metodos adicionales aqui si no lo incluye jpaRepository
}
