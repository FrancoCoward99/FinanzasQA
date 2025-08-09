package com.finanzas.service;

import com.finanzas.domain.Ingreso;
import java.util.List;

public interface IngresoService {

    List<Ingreso> getIngresos();

    Ingreso getIngreso(Ingreso ingreso);

    void save(Ingreso ingreso);

    List<Ingreso> getIngresosPorUsuario(Long idUsuario);

    Double obtenerTotalIngresos(Long idUsuario);

    void eliminarIngreso(Long idIngreso);

    List<Ingreso> getIngresosPorCategoria(Long idCategoria, Long idUsuario);
}
