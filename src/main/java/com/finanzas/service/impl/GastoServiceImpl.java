package com.finanzas.service.impl;

import com.finanzas.dao.GastoDao;
import com.finanzas.domain.Gasto;
import com.finanzas.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GastoServiceImpl implements GastoService {

    @Autowired
    private GastoDao gastoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Gasto> getGastos() {
        return gastoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Gasto getGasto(Gasto gasto) {
        return gastoDao.findById(gasto.getIdGasto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Gasto gasto) {
        gastoDao.save(gasto);
        gastoDao.registrarGastoEnHistorial(
                gasto.getUsuario().getIdUsuario(),
                gasto.getCategoria().getIdCategoria(),
                gasto.getMonto()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gasto> obtenerGastosPorUsuario(Long idUsuario) {
        return gastoDao.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Double obtenerTotalGastos(Long idUsuario) {
        Double total = gastoDao.obtenerTotalGastos(idUsuario);
        return total != null ? total : 0.0;
    }

    @Override
    @Transactional
    public void eliminarGasto(Long idGasto) {
        gastoDao.deleteById(idGasto);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long idGasto) {
        gastoDao.deleteById(idGasto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gasto> obtenerGastosPorCategoria(Long idCategoria, Long idUsuario) {
        return gastoDao.findByUsuarioAndCategoria(idUsuario, idCategoria);
    }
}
