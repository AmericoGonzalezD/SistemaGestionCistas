package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "psicologo")
public class Psicologo extends Usuario{
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

    public Psicologo(Long idUsuario, String nombre, String telefono, String direccion, int edad, String sexo, String password, Rol rol, Long idPsicologo, String cedulaProfesional, List<Especialidad> especialidades, Usuario usuario) {
        super(idUsuario, nombre, telefono, direccion, edad, sexo, password, rol);
        this.idPsicologo = idPsicologo;
        this.cedulaProfesional = cedulaProfesional;
        this.especialidades = especialidades;
        this.usuario = usuario;
    }

    public Long getIdPsicologo() {
        return idPsicologo;
    }

    public void setIdPsicologo(Long idPsicologo) {
        this.idPsicologo = idPsicologo;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
