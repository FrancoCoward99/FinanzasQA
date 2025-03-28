package com.finanzas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "tarjetas")
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarjeta")
    private Long idTarjeta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "numero_tarjeta", nullable = false)
    private String numeroTarjeta;

    @Column(nullable = false)
    private String tipo; // Débito o Crédito

    public Tarjeta() {
    }

    public Tarjeta(Usuario usuario, String nombre, String numeroTarjeta, String tipo) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.numeroTarjeta = numeroTarjeta;
        this.tipo = tipo;
    }
}
