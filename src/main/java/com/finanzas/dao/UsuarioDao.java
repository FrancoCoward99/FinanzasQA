package com.finanzas.dao;

import com.finanzas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    // Ya no se usan queries personalizadas; se trabaja con procedimientos
}
