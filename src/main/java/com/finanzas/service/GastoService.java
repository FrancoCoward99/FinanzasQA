package com.finanzas.service;

import com.finanzas.domain.Gasto;
import java.util.List;

public interface GastoService {

    List<Gasto> getGastos();

    Gasto getGasto(Gasto gasto);

    void save(Gasto gasto);

    List<Gasto> obtenerGastosPorUsuario(Long idUsuario);

    Double obtenerTotalGastos(Long idUsuario);

    void eliminarGasto(Long idGasto);

    void eliminarPorId(Long idGasto);
    
    List<Gasto> obtenerGastosPorCategoria(Long idCategoria, Long idUsuario);
}
