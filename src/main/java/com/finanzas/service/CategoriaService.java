package com.finanzas.service;

import com.finanzas.domain.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    
    // Guardar o actualizar una categoría
    Categoria guardarCategoria(Categoria categoria);
    Categoria getCategoria(Categoria categoria);
    
    // Obtener una categoría por su ID
    Optional<Categoria> obtenerCategoriaPorId(Long id);
    
    // Obtener todas las categorías de un usuario específico
    List<Categoria> obtenerCategoriasPorUsuario(Long idUsuario);
    
    // Eliminar una categoría por su ID
    void eliminarCategoria(Long id);
    
    List<Categoria> obtenerCategoriasDeIngresoPorUsuario(Long idUsuario);
    
    List<Categoria> obtenerCategoriasGastoPorUsuario(Long idUsuario);
}
