package com.finanzas.dao;

import com.finanzas.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {
    // Todo se maneja desde procedimientos almacenados ahora
}
