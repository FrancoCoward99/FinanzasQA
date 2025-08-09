package com.finanzas.service;

import com.finanzas.domain.Notificacion;
import java.util.List;

public interface NotificacionService {
    List<Notificacion> obtenerNotificaciones(Long idUsuario);
}
