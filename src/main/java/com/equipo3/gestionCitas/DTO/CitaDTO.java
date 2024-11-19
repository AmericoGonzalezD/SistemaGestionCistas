package com.equipo3.gestionCitas.DTO;

import com.equipo3.gestionCitas.models.Cita;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO {
    private Long idCita;
    private String clienteNombre;
    private String psicologoNombre;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    public CitaDTO() {
    }

    public CitaDTO(Long idCita, String clienteNombre, String psicologoNombre, LocalDate fecha, LocalTime hora, String estado) {
        this.idCita = idCita;
        this.clienteNombre = clienteNombre;
        this.psicologoNombre = psicologoNombre;
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

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getPsicologoNombre() {
        return psicologoNombre;
    }

    public void setPsicologoNombre(String psicologoNombre) {
        this.psicologoNombre = psicologoNombre;
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


// Constructor y getters/setters
}
