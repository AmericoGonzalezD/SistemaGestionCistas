package com.equipo3.gestionCitas.models;

import jakarta.persistence.*;

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "direccion", length = 60)
    private String direccion;

    @Column(name = "estado_civil", length = 15)
    private String estadoCivil;

    @Column(name = "tipo_suscripcion", length = 20)
    private String tipoSuscripcion; // Ejemplo de campo adicional

    // Relación OneToOne con la tabla Usuario
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_cliente") // Llave foránea que apunta a Usuario
    private Usuario usuario;
}
