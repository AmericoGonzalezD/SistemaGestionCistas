package com.equipo3.gestionCitas.DTO;

public class UsuarioClienteDTO {
    private Long idUsuario;
    private String nombre;
    private String correo;
    private String telefono;
    private int edad;
    private String sexo;
    private String rol;
    private String direccion; // Nuevo campo
    private String estadoCivil;

    public UsuarioClienteDTO() {
    }

    public UsuarioClienteDTO(Long idUsuario, String nombre, String correo, String telefono, int edad, String sexo, String rol, String direccion, String estadoCivil) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;
        this.sexo = sexo;
        this.rol = rol;
        this.direccion = direccion;
        this.estadoCivil = estadoCivil;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
