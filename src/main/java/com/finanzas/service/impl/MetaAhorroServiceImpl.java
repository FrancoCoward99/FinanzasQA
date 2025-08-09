package com.finanzas.service.impl;

import com.finanzas.dao.MetaAhorroDao;
import com.finanzas.domain.MetaAhorro;
import com.finanzas.service.MetaAhorroService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MetaAhorroServiceImpl implements MetaAhorroService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MetaAhorroDao metaAhorroDao;

    @Override
    public List<MetaAhorro> obtenerMetasPorUsuario(Long idUsuario) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("obtener_metas_por_usuario", MetaAhorro.class)
            .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        query.execute();
        return query.getResultList();
    }

    @Override
    public void guardarMeta(MetaAhorro meta) {
        metaAhorroDao.save(meta);
    }

    @Override
    public void eliminarMeta(Long idMeta) {
        metaAhorroDao.deleteById(idMeta);
    }
}
