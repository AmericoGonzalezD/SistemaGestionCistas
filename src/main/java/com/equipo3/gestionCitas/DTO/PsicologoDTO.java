package com.equipo3.gestionCitas.DTO;

public class PsicologoDTO {
    private Long id;
    private String cedulaProfesional;
    private String nombre; // Agregar campo para el nombre

    public PsicologoDTO(Long id, String cedulaProfesional, String nombre) {
        this.id = id;
        this.cedulaProfesional = cedulaProfesional;
        this.nombre = nombre;
    }

    public PsicologoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
