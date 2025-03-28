
package com.finanzas.service;

import com.finanzas.domain.Configuracion;
import java.util.Optional;


public interface ConfiguracionService {
    // Método para obtener la configuración por correo (específico para la configuración de usuario)
    Optional<Configuracion> obtenerConfiguracionPorCorreo(String correo);
    
    // Método para obtener la configuración por su ID
    Optional<Configuracion> obtenerConfiguracionPorId(Long idUsuario);
    
    // Método para actualizar los datos de configuración (nombre, correo, etc.)
    Configuracion actualizarConfiguracion(Configuracion configuracion);
    
    // Método para cambiar la contraseña de usuario desde la configuración
    Configuracion cambiarContrasena(Long idUsuario, String nuevaContrasena);
}