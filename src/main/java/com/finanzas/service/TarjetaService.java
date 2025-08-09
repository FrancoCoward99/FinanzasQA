package com.finanzas.service;

import com.finanzas.domain.Tarjeta;
import java.util.List;
import java.util.Optional;

public interface TarjetaService {

    Tarjeta guardarTarjeta(Tarjeta tarjeta);

    Optional<Tarjeta> obtenerTarjetaPorId(Long idTarjeta);

    List<Tarjeta> obtenerTarjetasPorUsuario(Long idUsuario);

    void eliminarTarjeta(Long idTarjeta);
}
