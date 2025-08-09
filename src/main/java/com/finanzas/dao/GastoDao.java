package com.finanzas.dao;

import com.finanzas.domain.Gasto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GastoDao extends JpaRepository<Gasto, Long> {

    @Procedure(name = "registrarGastoHistorial")
void registrarGastoHistorial(
    @Param("p_id_usuario") Long idUsuario,
    @Param("p_id_categoria") Long idCategoria,
    @Param("p_monto") Double monto
);
    

}
