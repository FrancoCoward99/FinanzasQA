package com.finanzas.dao;

import com.finanzas.domain.MetaAhorro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaAhorroDao extends JpaRepository<MetaAhorro, Long> {
    // Ya no contiene consultas personalizadas; se usa procedimiento almacenado
}
