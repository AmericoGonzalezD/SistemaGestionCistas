package com.equipo3.gestionCitas.models;
import java.io.Serializable;
import java.util.Objects;

public class PsicologoEspecialidadId implements Serializable {

    private Long idPsicologo; // Cambiar tipo a Long
    private Long idEspecialidad; // Cambiar tipo a Long

    public PsicologoEspecialidadId() {}

    public Long getIdPsicologo() {
        return idPsicologo;
    }

    public void setIdPsicologo(Long idPsicologo) {
        this.idPsicologo = idPsicologo;
    }

    public Long getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Long idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsicologoEspecialidadId that = (PsicologoEspecialidadId) o;
        return Objects.equals(idPsicologo, that.idPsicologo) &&
                Objects.equals(idEspecialidad, that.idEspecialidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPsicologo, idEspecialidad);
    }
}

