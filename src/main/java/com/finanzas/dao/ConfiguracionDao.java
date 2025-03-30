package com.finanzas.dao;

import com.finanzas.domain.Configuracion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConfiguracionDao extends JpaRepository<Configuracion, Long> {
    
    // Buscar usuario por correo electrónico (para validaciones y autenticación)
    Optional<Configuracion> findByCorreo(String correo);

    // Buscar usuario por correo y contraseña (para validación de inicio de sesión)
    Optional<Configuracion> findByCorreoAndContrasena(String correo, String contrasena);
}
