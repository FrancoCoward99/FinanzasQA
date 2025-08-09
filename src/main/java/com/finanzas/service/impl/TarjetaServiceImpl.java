package com.finanzas.service.impl;

import com.finanzas.dao.TarjetaDao;
import com.finanzas.domain.Tarjeta;
import com.finanzas.service.TarjetaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TarjetaDao tarjetaDao;

    @Override
    public Tarjeta guardarTarjeta(Tarjeta tarjeta) {
        return tarjetaDao.save(tarjeta);
    }

    @Override
    public Optional<Tarjeta> obtenerTarjetaPorId(Long idTarjeta) {
        return tarjetaDao.findById(idTarjeta);
    }

    @Override
    public List<Tarjeta> obtenerTarjetasPorUsuario(Long idUsuario) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("obtener_tarjetas_por_usuario", Tarjeta.class)
            .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        query.execute();
        return query.getResultList();
    }

    @Override
    public void eliminarTarjeta(Long idTarjeta) {
        tarjetaDao.deleteById(idTarjeta);
    }
}
