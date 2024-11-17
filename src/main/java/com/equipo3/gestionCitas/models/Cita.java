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
}
