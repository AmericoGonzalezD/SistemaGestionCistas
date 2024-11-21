package com.equipo3.gestionCitas.DTO;

public class ActualizarUsuarioRequest {
    private String correo;
    private String telefono;
    private String direccion;
    private String estadoCivil;

    // Getters y Setters

    public ActualizarUsuarioRequest(String correo, String telefono, String direccion, String estadoCivil) {
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estadoCivil = estadoCivil;
    }

    public ActualizarUsuarioRequest() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
}