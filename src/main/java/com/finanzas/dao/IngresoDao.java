package com.finanzas.dao;

import com.finanzas.domain.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoDao extends JpaRepository<Ingreso, Long> {
    // Ya no se usan queries personalizadas. Todo se maneja con procedimientos almacenados.
}


