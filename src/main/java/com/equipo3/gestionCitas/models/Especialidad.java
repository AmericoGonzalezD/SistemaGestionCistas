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

    public Especialidad(String nombre, List<PsicologoEspecialidad> psicologos) {
        this.nombre = nombre;
        this.psicologos = psicologos;
    }

    public Especialidad() {
    }

    public Long getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Long idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PsicologoEspecialidad> getPsicologos() {
        return psicologos;
    }

    public void setPsicologos(List<PsicologoEspecialidad> psicologos) {
        this.psicologos = psicologos;
    }
}