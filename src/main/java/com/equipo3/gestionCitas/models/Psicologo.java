package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "psicologo")
public class Psicologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_psicologo")
    private Long idPsicologo;
    @Column(name = "cedula_profesional")
    private String cedulaProfesional;

    // Relación ManyToMany con la tabla Especialidad
    @ManyToMany
    @JoinTable(
            name = "psicologo_especialidad", // Tabla intermedia
            joinColumns = @JoinColumn(name = "id_psicologo"), // Llave foránea a Psicologo
            inverseJoinColumns = @JoinColumn(name = "id_especialidad") // Llave foránea a Especialidad
    )
    private List<Especialidad> especialidades;

    // Relación OneToOne con la tabla Usuario
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_psicologo") // Llave foránea que apunta a Usuario
    private Usuario usuario;

    public Psicologo() {
    }

}
