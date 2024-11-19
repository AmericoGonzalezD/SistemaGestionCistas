package com.equipo3.gestionCitas.repositories;

import com.equipo3.gestionCitas.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findByRol(String rol);
}
