package com.finanzas.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


@Data 
@Entity 
@Table(name="gastos")
public class Gasto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long idGasto;
    private Double monto;
    private String descripcion; 
    
    @Column(name = "fecha", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fecha;
    
    
//    @ManyToOne
//    @JoinColumn(name="id_usuario")
//    private Usuario usuario;
    
//        @ManyToOne
//    @JoinColumn(name="id_categoria")
//    private Categoria categoria;
    
}


