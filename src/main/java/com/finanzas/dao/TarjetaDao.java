package com.finanzas.dao;

import com.finanzas.domain.Tarjeta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaDao extends JpaRepository<Tarjeta, Long> {

    // Obtener todas las tarjetas de un usuario específico
    List<Tarjeta> findByUsuarioIdUsuario(Long idUsuario);
}
