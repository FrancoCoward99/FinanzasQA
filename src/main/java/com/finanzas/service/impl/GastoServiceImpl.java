/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finanzas.service.impl;

import com.finanzas.dao.GastoDao;
import com.finanzas.domain.Gasto;
import com.finanzas.service.GastoService;
import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GastoServiceImpl implements GastoService {

    @Autowired
    private GastoDao gastoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Gasto> getGastos() {
        var lista = gastoDao.findAll();

        return lista;
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
        gastoDao.registrarGastoEnHistorial(gasto.getUsuario().getIdUsuario(),
                gasto.getCategoria().getIdCategoria(),
                gasto.getMonto());
    }

    @Override
    public List<Gasto> getGastosPorUsuario(Long idUsuario) {
        return gastoDao.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public Double obtenerTotalGastos(Long idUsuario) {
        Double total = gastoDao.obtenerTotalGastos(idUsuario);
        return total != null ? total : 0.0;
    }

    @Override
    public void eliminarGasto(Long idGasto) {
        gastoDao.deleteById(idGasto);
    }

}
