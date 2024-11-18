package com.equipo3.gestionCitas.repositories;

import com.equipo3.gestionCitas.models.PsicologoEspecialidad;
import com.equipo3.gestionCitas.models.PsicologoEspecialidadId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsicologoEspecialidadRepository extends JpaRepository<PsicologoEspecialidad, PsicologoEspecialidadId> {
}
