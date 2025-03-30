package com.finanzas.service.impl;

import com.finanzas.dao.ConfiguracionDao;
import com.finanzas.domain.Configuracion;
import com.finanzas.service.ConfiguracionService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracionServiceImp implements ConfiguracionService {

    @Autowired
    private ConfiguracionDao configuracionDao;

    @Override
    public Optional<Configuracion> obtenerConfiguracionPorCorreo(String correo) {
        return configuracionDao.findByCorreo(correo);
    }

    @Override
    public Optional<Configuracion> obtenerConfiguracionPorId(Long idUsuario) {
        return configuracionDao.findById(idUsuario);
    }

    @Override
    public Configuracion actualizarConfiguracion(Configuracion configuracion) {
        return configuracionDao.save(configuracion);  // Guarda o actualiza la configuración del usuario
    }

    @Override
    public Configuracion cambiarContrasena(Long idUsuario, String nuevaContrasena) {
        Optional<Configuracion> configuracionOpt = configuracionDao.findById(idUsuario);
        if (configuracionOpt.isPresent()) {
            Configuracion configuracion = configuracionOpt.get();
            configuracion.setContrasena(nuevaContrasena);  // Cambia la contraseña
            return configuracionDao.save(configuracion);  // Guarda la configuración actualizada
        }
        return null;  // Si no se encuentra la configuración del usuario
    }
}