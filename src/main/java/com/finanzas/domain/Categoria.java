
package com.finanzas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria; // id_categoria en la base de datos

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario; // Relación con la entidad Usuario

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCategoria tipo; // ENUM('INGRESO', 'GASTO')

    public Categoria() {
    }

    public Categoria(Usuario usuario, String nombre, TipoCategoria tipo) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.tipo = tipo;
    }
}

