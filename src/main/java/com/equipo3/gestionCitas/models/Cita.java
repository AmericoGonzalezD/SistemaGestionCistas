package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_psicologo")
    private Psicologo psicologo;
    private String nombre;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    public Cita() {
    }

    public Cita(Long idCita, Cliente cliente, Psicologo psicologo, String nombre, LocalDate fecha, LocalTime hora, String estado) {
        this.idCita = idCita;
        this.cliente = cliente;
        this.psicologo = psicologo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Psicologo getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
