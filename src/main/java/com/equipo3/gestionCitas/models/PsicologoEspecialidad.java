package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PSICOLOGO_ESPECIALIDAD")
public class PsicologoEspecialidad {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_psicologo")
    private Psicologo psicologo;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;
}
