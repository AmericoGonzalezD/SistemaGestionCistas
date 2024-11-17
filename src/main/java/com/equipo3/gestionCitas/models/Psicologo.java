package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "psicologo")
public class Psicologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_psicologo")
    private Long idPsicologo;
    private String nombre;
    private String telefono ;
    private int edad;
    private String correo;
    private String sexo;
    @Column(name = "cedula_profesional")
    private String cedulaProfesional;

    public Psicologo() {
    }

    public Psicologo(Long id, String nombre, String telefono, int edad, String correo, String sexo, String cedulaProfesional) {
        this.idPsicologo = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
        this.correo = correo;
        this.sexo = sexo;
        this.cedulaProfesional = cedulaProfesional;
    }

    public Long getIdPsicologo() {
        return idPsicologo;
    }

    public void setIdPsicologo(Long id) {
        this.idPsicologo = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }
}
