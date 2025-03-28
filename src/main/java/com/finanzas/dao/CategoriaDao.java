package com.finanzas.dao;

import com.finanzas.domain.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {
    
    // Obtener todas las categorías de un usuario en específico
    List<Categoria> findByUsuarioIdUsuario(Long idUsuario);

    // Buscar categorías por nombre (puede haber varias con el mismo nombre)
    List<Categoria> findByNombre(String nombre);

    // Buscar categorías por tipo ("INGRESO" o "GASTO")
    List<Categoria> findByTipo(String tipo);
}
