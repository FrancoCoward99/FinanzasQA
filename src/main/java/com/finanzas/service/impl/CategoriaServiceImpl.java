package com.finanzas.service.impl;

import com.finanzas.dao.CategoriaDao;
import com.finanzas.domain.Categoria;
import com.finanzas.domain.TipoCategoria;
import com.finanzas.service.CategoriaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaDao.save(categoria);
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaDao.findById(id);
    }

    @Override
    public List<Categoria> obtenerCategoriasPorUsuario(Long idUsuario) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("obtener_categorias_por_usuario", Categoria.class)
                .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        query.execute();
        return query.getResultList();
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaDao.deleteById(id);
    }

    @Override
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    public List<Categoria> obtenerCategoriasDeIngresoPorUsuario(Long idUsuario) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("obtener_categorias_usuario_tipo", Categoria.class)
                .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_tipo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        query.setParameter("p_tipo", "INGRESO");
        query.execute();
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> obtenerCategoriasPorTipo(Long idUsuario, String tipo) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("obtener_categorias_usuario_tipo", Categoria.class)
                .registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_tipo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", idUsuario);
        query.setParameter("p_tipo", tipo);
        query.execute();

        return query.getResultList();
    }

}
