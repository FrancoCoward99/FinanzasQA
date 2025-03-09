package com.finanzas.service;

import com.finanzas.domain.Usuario;
import java.util.Optional;

public interface UsuarioService {
    void registrarUsuario(Usuario usuario);
    
    // Método para autenticar usuario
    Optional<Usuario> autenticarUsuario(String correo, String contrasena);

    Optional<Usuario> obtenerUsuarioPorId(Long idUsuario);
}
