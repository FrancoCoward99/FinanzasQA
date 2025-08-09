package com.finanzas.service.impl;

import com.finanzas.domain.Notificacion;
import com.finanzas.service.NotificacionService;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Notificacion> obtenerNotificaciones(Long idUsuario) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("obtener_notificaciones", Notificacion.class)
            .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        return query.getResultList();
    }
}
