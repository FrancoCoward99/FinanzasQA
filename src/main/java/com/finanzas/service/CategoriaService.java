package com.finanzas.service;

import com.finanzas.domain.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    Categoria guardarCategoria(Categoria categoria);

    Optional<Categoria> obtenerCategoriaPorId(Long id);

    List<Categoria> obtenerCategoriasPorUsuario(Long idUsuario);

    void eliminarCategoria(Long id);

    Categoria getCategoria(Categoria categoria);

    List<Categoria> obtenerCategoriasDeIngresoPorUsuario(Long idUsuario);

  
    List<Categoria> obtenerCategoriasPorTipo(Long idUsuario, String tipo);

}
