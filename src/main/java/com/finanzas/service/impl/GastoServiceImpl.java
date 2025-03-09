/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finanzas.service.impl;

import com.finanzas.dao.GastoDao;
import com.finanzas.domain.Gasto;
import com.finanzas.service.GastoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GastoServiceImpl implements GastoService {
    
    @Autowired
    private GastoDao gastoDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Gasto> getGastos() {
        var lista = gastoDao.findAll();

        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Gasto getGasto(Gasto gasto) {
        return gastoDao.findById(gasto.getIdGasto()).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Gasto gasto){
        gastoDao.save(gasto);
        
    }


    
}