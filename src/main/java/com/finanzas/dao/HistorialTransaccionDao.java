package com.finanzas.dao;

import com.finanzas.domain.HistorialTransaccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistorialTransaccionDao extends JpaRepository<HistorialTransaccion, Long> {

    @Query("SELECT h FROM HistorialTransaccion h WHERE h.usuario.idUsuario = :idUsuario ORDER BY h.fecha DESC")
    List<HistorialTransaccion> obtenerHistorialPorUsuario(@Param("idUsuario") Long idUsuario);

}
