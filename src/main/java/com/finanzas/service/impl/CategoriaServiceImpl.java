package com.finanzas.service.impl;

import com.finanzas.dao.CategoriaDao;
import com.finanzas.domain.Categoria;
import com.finanzas.domain.TipoCategoria;
import com.finanzas.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

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
        return categoriaDao.findByUsuarioIdUsuario(idUsuario);
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
        return categoriaDao.findByUsuario_IdUsuarioAndTipo(idUsuario, TipoCategoria.INGRESO);
    }
}
