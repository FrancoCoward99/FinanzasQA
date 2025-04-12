package com.finanzas.dao;

import com.finanzas.domain.Ingreso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IngresoDao extends JpaRepository<Ingreso, Long> {

    List<Ingreso> findByUsuario_IdUsuario(Long idUsuario);

    @Query("SELECT SUM(i.monto) FROM Ingreso i WHERE i.usuario.idUsuario = :idUsuario")
    Double obtenerTotalIngresos(@Param("idUsuario") Long idUsuario);

    @Query(value = "INSERT INTO historial_transacciones (id_usuario, tipo, id_categoria, monto, fecha) "
            + "VALUES (:idUsuario, 'INGRESO', :idCategoria, :monto, NOW())", nativeQuery = true)
    @Modifying
    @Transactional
    void registrarIngresoEnHistorial(@Param("idUsuario") Long idUsuario,
            @Param("idCategoria") Long idCategoria,
            @Param("monto") Double monto);

     //Ejemplo de método utilizando Métodos de Query

    List<Ingreso> findByCategoria_IdCategoriaAndUsuario_IdUsuario(Long idCategoria, Long idUsuario);
    

    
}
