package com.equipo3.gestionCitas.repositories;

import com.equipo3.gestionCitas.models.Cita;
import com.equipo3.gestionCitas.models.Cliente;
import com.equipo3.gestionCitas.models.Psicologo;
import com.equipo3.gestionCitas.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    //se pueden declarar los metodos adicionales aqui si no lo incluye jpaRepository
    List<Cita> findByCliente(Cliente cliente);
    List<Cita> findByPsicologo(Psicologo psicologo);
    List<Cita> findByUsuario(Usuario usuario);

}
