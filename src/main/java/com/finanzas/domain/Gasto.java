package com.finanzas.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@NamedStoredProcedureQuery(
    name = "registrarGastoHistorial",
    procedureName = "registrar_gasto_historial",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_usuario", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_categoria", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_monto", type = Double.class)
    }
)
@Data
@Entity
@Table(name = "gastos")
public class Gasto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGasto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_tarjeta")
    private Tarjeta tarjeta;

    private Double monto;

    private String descripcion;

    @Temporal(TemporalType.DATE)
    private Date fecha;
}
