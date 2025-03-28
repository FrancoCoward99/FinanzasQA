package com.finanzas.service;

import com.finanzas.domain.Tarjeta;
import java.util.List;
import java.util.Optional;

public interface TarjetaService {

    // Guardar o actualizar una tarjeta
    Tarjeta guardarTarjeta(Tarjeta tarjeta);

    // Obtener una tarjeta por su ID
    Optional<Tarjeta> obtenerTarjetaPorId(Long idTarjeta);

    // Obtener todas las tarjetas de un usuario específico
    List<Tarjeta> obtenerTarjetasPorUsuario(Long idUsuario);

    // Eliminar una tarjeta por su ID
    void eliminarTarjeta(Long idTarjeta);
}
