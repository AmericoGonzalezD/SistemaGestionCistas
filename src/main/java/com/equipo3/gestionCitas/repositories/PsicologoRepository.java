package com.equipo3.gestionCitas.repositories;

import com.equipo3.gestionCitas.models.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PsicologoRepository extends JpaRepository<Psicologo, Long> {
    //se pueden declarar los metodos adicionales aqui si no lo incluye jpaRepository
    Optional<Psicologo> findByUsuarioCorreo(String correo);
}
