package com.finanzas.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "historial_transacciones")
public class HistorialTransaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private String tipo; // 'INGRESO' o 'GASTO'

    @Column(nullable = false)
    private Double monto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
}
