package com.finanzas.service.impl;

import com.finanzas.dao.UsuarioDao;
import com.finanzas.domain.Usuario;
import com.finanzas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public Optional<Usuario> autenticarUsuario(String correo, String contrasena) {
        return usuarioDao.findByCorreoAndContrasena(correo, contrasena);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long idUsuario) {
        return usuarioDao.findById(idUsuario);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    public void eliminarUsuarioPorId(Long idUsuario) {
        usuarioDao.deleteById(idUsuario);
    }

    @Override
    public void cambiarEstadoUsuario(Long idUsuario) {
        Optional<Usuario> usuarioOpt = usuarioDao.findById(idUsuario);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setActivo(!usuario.isActivo());
            usuarioDao.save(usuario);
        }
    }
}
