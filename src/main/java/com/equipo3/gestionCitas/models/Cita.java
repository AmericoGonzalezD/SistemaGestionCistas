package com.equipo3.gestionCitas.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

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
}
