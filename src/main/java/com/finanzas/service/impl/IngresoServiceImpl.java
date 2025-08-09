package com.finanzas.service.impl;

import com.finanzas.domain.Ingreso;
import com.finanzas.service.IngresoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class IngresoServiceImpl implements IngresoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Ingreso> getIngresos() {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("obtener_ingresos", Ingreso.class)
            .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Ingreso getIngreso(Ingreso ingreso) {
        return entityManager.find(Ingreso.class, ingreso.getIdIngreso());
    }

    @Override
    @Transactional
    public void save(Ingreso ingreso) {
        entityManager.persist(ingreso);

        StoredProcedureQuery spQuery = entityManager
            .createStoredProcedureQuery("registrar_ingreso_historial")
            .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_id_categoria", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_monto", Double.class, ParameterMode.IN);

        spQuery.setParameter("p_id_usuario", ingreso.getUsuario().getIdUsuario());
        spQuery.setParameter("p_id_categoria", ingreso.getCategoria().getIdCategoria());
        spQuery.setParameter("p_monto", ingreso.getMonto());

        spQuery.execute();
    }

    @Override
    public List<Ingreso> getIngresosPorUsuario(Long idUsuario) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("obtener_ingresos_por_usuario", Ingreso.class)
            .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        query.execute();
        return query.getResultList();
    }

@Override
@Transactional(readOnly = true)
public Double obtenerTotalIngresos(Long idUsuario) {
    StoredProcedureQuery query = entityManager
        .createStoredProcedureQuery("obtener_total_ingresos")
        .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
        .registerStoredProcedureParameter(2, Double.class, ParameterMode.OUT);

    query.setParameter(1, idUsuario);
    query.execute();

    Double total = (Double) query.getOutputParameterValue(2);
    return total != null ? total : 0.0;
}

    @Override
    public void eliminarIngreso(Long idIngreso) {
        Ingreso ingreso = entityManager.find(Ingreso.class, idIngreso);
        if (ingreso != null) {
            entityManager.remove(ingreso);
        }
    }

    @Override
    public List<Ingreso> getIngresosPorCategoria(Long idCategoria, Long idUsuario) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("ingresos_por_usuario_categoria_dinamico", Ingreso.class)
            .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_id_categoria", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        query.setParameter("p_id_categoria", idCategoria);
        query.execute();
        return query.getResultList();
    }
}
