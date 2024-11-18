package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PSICOLOGO")
public class Psicologo {

    @Id
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_psicologo")
    private Usuario usuario;

    @Column(name = "cedula_profesional")
    private String cedulaProfesional;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "psicologo")
    private List<PsicologoEspecialidad> especialidades;

    @OneToMany(mappedBy = "psicologo")
    private List<Cita> citas;

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<PsicologoEspecialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<PsicologoEspecialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
