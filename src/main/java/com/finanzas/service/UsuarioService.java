package com.finanzas.service;

import com.finanzas.domain.Usuario;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> autenticarUsuario(String correo, String contrasena);
    void registrarUsuario(Usuario usuario);
    Optional<Usuario> obtenerUsuarioPorId(Long idUsuario);
    void actualizarUsuario(Usuario usuario);
}
