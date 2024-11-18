package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ESPECIALIDAD")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Long idEspecialidad;

    private String nombre;

    @OneToMany(mappedBy = "especialidad")
    private List<PsicologoEspecialidad> psicologos;


}