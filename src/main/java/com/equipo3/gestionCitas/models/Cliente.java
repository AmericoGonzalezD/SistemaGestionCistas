package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

public class Cliente extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "direccion", length = 60)
    private String direccion;

    @Column(name = "estado_civil", length = 15)
    private String estadoCivil;

    // Relación OneToOne con la tabla Usuario
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_cliente") // Llave foránea que apunta a Usuario
    private Usuario usuario;

}
