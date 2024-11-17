package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "psicologo")
public class Psicologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String telefono ;
    private int edad;
    private String correo;
    private String sexo;
    @Column(name = "cedula_profesional")
    private String cedulaProfesional;

}
