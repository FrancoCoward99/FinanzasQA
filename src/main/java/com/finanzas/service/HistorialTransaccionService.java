package com.finanzas.service;

import com.finanzas.domain.HistorialTransaccion;
import java.util.List;

public interface HistorialTransaccionService {
    List<HistorialTransaccion> obtenerHistorialPorUsuario(Long idUsuario);
}
