package com.finanzas.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "metas_ahorro")
public class MetaAhorro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meta")
    private Long idMeta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "monto_objetivo", nullable = false)
    private Double montoObjetivo;

    @Column(name = "fecha_limite", nullable = false)
    private java.sql.Date fechaLimite;

    @Column(nullable = false)
    private int meses;
}