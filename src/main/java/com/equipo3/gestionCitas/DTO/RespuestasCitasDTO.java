package com.equipo3.gestionCitas.DTO;

import java.util.List;

public class RespuestasCitasDTO {
    private Long idPsicologo;
    private List<CitaDTO> citas;

    public RespuestasCitasDTO(Long idPsicologo, List<CitaDTO> citas) {
        this.idPsicologo = idPsicologo;
        this.citas = citas;
    }

    // Getters y Setters
    public Long getIdPsicologo() {
        return idPsicologo;
    }

    public void setIdPsicologo(Long idPsicologo) {
        this.idPsicologo = idPsicologo;
    }

    public List<CitaDTO> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaDTO> citas) {
        this.citas = citas;
    }
}
