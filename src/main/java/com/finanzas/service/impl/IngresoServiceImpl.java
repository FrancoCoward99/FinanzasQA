/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finanzas.service.impl;

import com.finanzas.dao.IngresoDao;
import com.finanzas.domain.Ingreso;
import com.finanzas.service.IngresoService;
import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngresoServiceImpl implements IngresoService {

    @Autowired
    private IngresoDao ingresoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ingreso> getIngresos() {
        var lista = ingresoDao.findAll();

        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Ingreso getIngreso(Ingreso ingreso) {
        return ingresoDao.findById(ingreso.getIdIngreso()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Ingreso ingreso) {
        ingresoDao.save(ingreso);
        ingresoDao.registrarIngresoEnHistorial(ingreso.getUsuario().getIdUsuario(),
                ingreso.getCategoria().getIdCategoria(),
                ingreso.getMonto());
    }

    @Override
    public List<Ingreso> getIngresosPorUsuario(Long idUsuario) {
        return ingresoDao.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public Double obtenerTotalIngresos(Long idUsuario) {
        Double total = ingresoDao.obtenerTotalIngresos(idUsuario);
        return total != null ? total : 0.0;
    }

    @Override
    public void eliminarIngreso(Long idIngreso) {
        ingresoDao.deleteById(idIngreso);
    }

    

}
