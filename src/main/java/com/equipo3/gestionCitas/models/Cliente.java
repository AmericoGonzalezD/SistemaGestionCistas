package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_cliente")
    private Usuario usuario;

    private String direccion;
    private String estadoCivil;

    @OneToMany(mappedBy = "cliente")
    private List<Cita> citas;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
