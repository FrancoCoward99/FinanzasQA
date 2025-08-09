package com.finanzas.dao;

import com.finanzas.domain.HistorialTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialTransaccionDao extends JpaRepository<HistorialTransaccion, Long> {
    // Ya no contiene consultas directas, todo se delega a procedimientos almacenados
}
