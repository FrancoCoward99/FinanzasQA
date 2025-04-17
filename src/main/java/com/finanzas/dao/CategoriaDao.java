package com.finanzas.dao;

import com.finanzas.domain.Categoria;
import com.finanzas.domain.TipoCategoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {

    // Obtener todas las categorías de un usuario en específico
    List<Categoria> findByUsuarioIdUsuario(Long idUsuario);

    // Buscar categorías por nombre (puede haber varias con el mismo nombre)
    List<Categoria> findByNombre(String nombre);

    // Buscar categorías por tipo ("INGRESO" o "GASTO")
    List<Categoria> findByTipo(String tipo);

    List<Categoria> findByUsuario_IdUsuarioAndTipo(Long idUsuario, TipoCategoria tipo);

    @Query("SELECT c FROM Categoria c WHERE c.usuario.idUsuario = :idUsuario AND c.tipo = 'GASTO'")
    List<Categoria> findCategoriasGastoPorUsuario(@Param("idUsuario") Long idUsuario);
}
