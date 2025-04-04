/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finanzas.service.impl;



import com.finanzas.dao.MetaAhorroDao;
import com.finanzas.domain.MetaAhorro;
import com.finanzas.service.MetaAhorroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaAhorroServiceImpl implements MetaAhorroService {

    @Autowired
    private MetaAhorroDao metaAhorroDao;

    @Override
    public List<MetaAhorro> obtenerMetasPorUsuario(Long idUsuario) {
        return metaAhorroDao.findByUsuarioIdUsuario(idUsuario);
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
