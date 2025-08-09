package com.finanzas.service.impl;

import com.finanzas.domain.Gasto;
import com.finanzas.service.GastoService;
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
public class GastoServiceImpl implements GastoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Gasto> getGastos() {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("obtener_gastos", Gasto.class)
            .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Gasto getGasto(Gasto gasto) {
        return entityManager.find(Gasto.class, gasto.getIdGasto());
    }

    @Override
    @Transactional
    public void save(Gasto gasto) {
        entityManager.persist(gasto);

        // Ejecutar procedimiento almacenado para el historial
        StoredProcedureQuery spQuery = entityManager
            .createStoredProcedureQuery("registrar_gasto_historial")
            .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_id_categoria", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_monto", Double.class, ParameterMode.IN);

        spQuery.setParameter("p_id_usuario", gasto.getUsuario().getIdUsuario());
        spQuery.setParameter("p_id_categoria", gasto.getCategoria().getIdCategoria());
        spQuery.setParameter("p_monto", gasto.getMonto());

        spQuery.execute();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gasto> obtenerGastosPorUsuario(Long idUsuario) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("obtener_gastos_por_usuario", Gasto.class)
            .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        query.execute();
        return query.getResultList();
    }

@Override
@Transactional(readOnly = true)
public Double obtenerTotalGastos(Long idUsuario) {
    StoredProcedureQuery query = entityManager
        .createStoredProcedureQuery("obtener_total_gastos")
        .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
        .registerStoredProcedureParameter(2, Double.class, ParameterMode.OUT);

    query.setParameter(1, idUsuario);
    query.execute();

    Double total = (Double) query.getOutputParameterValue(2);
    return total != null ? total : 0.0;
}


    @Override
    @Transactional
    public void eliminarGasto(Long idGasto) {
        entityManager.remove(entityManager.find(Gasto.class, idGasto));
    }

    @Override
    @Transactional
    public void eliminarPorId(Long idGasto) {
        eliminarGasto(idGasto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gasto> obtenerGastosPorCategoria(Long idCategoria, Long idUsuario) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("gastos_por_usuario_categoria_dinamico", Gasto.class)
            .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_id_categoria", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        query.setParameter("p_id_categoria", idCategoria);
        query.execute();
        return query.getResultList();
    }
}
