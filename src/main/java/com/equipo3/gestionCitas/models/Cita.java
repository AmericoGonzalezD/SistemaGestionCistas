package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "CITA")
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

    private LocalDate fecha;
    private LocalTime hora;

    private String estado; // Estados: "Pendiente", "Confirmada", "Cancelada", etc.

    @ManyToOne // Relación muchos a uno con Usuario
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    public Cita() {
    }


}
