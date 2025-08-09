package com.finanzas.service.impl;

import com.finanzas.dao.HistorialTransaccionDao;
import com.finanzas.domain.HistorialTransaccion;
import com.finanzas.service.HistorialTransaccionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HistorialTransaccionServiceImpl implements HistorialTransaccionService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private HistorialTransaccionDao historialTransaccionDao;

    @Override
    public List<HistorialTransaccion> obtenerHistorialPorUsuario(Long idUsuario) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("obtener_historial_usuario", HistorialTransaccion.class)
            .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        query.execute();
        return query.getResultList();
    }
}
