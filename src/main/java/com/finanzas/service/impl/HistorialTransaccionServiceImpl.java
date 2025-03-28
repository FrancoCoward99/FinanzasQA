package com.finanzas.service.impl;

import com.finanzas.dao.HistorialTransaccionDao;
import com.finanzas.domain.HistorialTransaccion;
import com.finanzas.service.HistorialTransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HistorialTransaccionServiceImpl implements HistorialTransaccionService {

    @Autowired
    private HistorialTransaccionDao historialTransaccionDao;

    @Override
    public List<HistorialTransaccion> obtenerHistorialPorUsuario(Long idUsuario) {
        return historialTransaccionDao.obtenerHistorialPorUsuario(idUsuario);
    }
}
