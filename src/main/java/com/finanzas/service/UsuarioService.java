package com.finanzas.service;

import com.finanzas.domain.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    void registrarUsuario(Usuario usuario);

    Optional<Usuario> autenticarUsuario(String correo, String contrasena);

    Optional<Usuario> obtenerUsuarioPorId(Long idUsuario);

    Usuario actualizarUsuario(Usuario usuario);

    List<Usuario> obtenerTodosLosUsuarios();

    void eliminarUsuarioPorId(Long idUsuario);

    void cambiarEstadoUsuario(Long idUsuario);
}
