/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finanzas.domain;





import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario; // id_usuario en la base de datos

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contrasena; // Se recomienda almacenar encriptada

    private boolean activo = true;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String contrasena, boolean activo) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.activo = activo;
    }
}

