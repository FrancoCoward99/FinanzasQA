package com.finanzas.dao;

import com.finanzas.domain.Gasto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GastoDao extends JpaRepository<Gasto, Long> {

    List<Gasto> findByUsuario_IdUsuario(Long idUsuario);

    @Query("SELECT SUM(g.monto) FROM Gasto g WHERE g.usuario.idUsuario = :idUsuario")
    Double obtenerTotalGastos(@Param("idUsuario") Long idUsuario);

    @Query(value = "INSERT INTO historial_transacciones (id_usuario, tipo, id_categoria, monto, fecha) "
            + "VALUES (:idUsuario, 'GASTO', :idCategoria, :monto, NOW())", nativeQuery = true)
    @Modifying
    @Transactional
    void registrarGastoEnHistorial(@Param("idUsuario") Long idUsuario,
            @Param("idCategoria") Long idCategoria,
            @Param("monto") Double monto);

    @Query("SELECT g FROM Gasto g WHERE g.usuario.idUsuario = :idUsuario AND g.categoria.idCategoria = :idCategoria")
    List<Gasto> findByUsuarioAndCategoria(@Param("idUsuario") Long idUsuario, @Param("idCategoria") Long idCategoria);
    
    
}
