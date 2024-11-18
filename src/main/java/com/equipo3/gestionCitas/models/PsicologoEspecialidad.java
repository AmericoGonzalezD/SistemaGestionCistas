package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

@Entity
@IdClass(PsicologoEspecialidadId.class)
@Table(name = "psicologo_especialidad")
public class PsicologoEspecialidad {

    @Id
    @Column(name = "id_psicologo")
    private Long idPsicologo;
    @Id
    @Column(name = "id_especialidad")
    private Long idEspecialidad;

    @ManyToOne
    @JoinColumn(name = "id_psicologo", referencedColumnName = "id_psicologo", insertable = false, updatable = false)
    private Psicologo psicologo;
    @ManyToOne
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad", insertable = false, updatable = false)
    private Especialidad especialidad;


    public PsicologoEspecialidad() {}

    public PsicologoEspecialidad(Long idPsicologo, Long idEspecialidad) {
        this.idPsicologo = idPsicologo;
        this.idEspecialidad = idEspecialidad;
    }

    public Long getIdPsicologo() {
        return idPsicologo;
    }

    public void setIdPsicologo(Long idPsicologo) {
        this.idPsicologo = idPsicologo;
    }

    public Long getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Long especialidadId) {
        this.idEspecialidad = especialidadId;
    }


}
