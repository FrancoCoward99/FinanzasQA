package com.finanzas.service.impl;

import com.finanzas.dao.TarjetaDao;
import com.finanzas.domain.Tarjeta;
import com.finanzas.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaServiceImpl implements TarjetaService {

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
        return tarjetaDao.findByUsuarioIdUsuario(idUsuario);
    }

    @Override
    public void eliminarTarjeta(Long idTarjeta) {
        tarjetaDao.deleteById(idTarjeta);
    }
}
