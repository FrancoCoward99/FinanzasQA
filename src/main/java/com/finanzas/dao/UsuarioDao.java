package com.finanzas.dao;

import com.finanzas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    
    // Buscar usuario por correo electrónico (para validaciones y autenticación)
    Optional<Usuario> findByCorreo(String correo);

    // Buscar usuario por correo y contraseña (para validación de inicio de sesión)
    Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);
}
