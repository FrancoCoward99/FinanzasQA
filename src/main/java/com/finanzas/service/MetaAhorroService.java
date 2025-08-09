package com.finanzas.service;

import com.finanzas.domain.MetaAhorro;
import java.util.List;

public interface MetaAhorroService {
    List<MetaAhorro> obtenerMetasPorUsuario(Long idUsuario);
    void guardarMeta(MetaAhorro meta);
    void eliminarMeta(Long idMeta);
}
