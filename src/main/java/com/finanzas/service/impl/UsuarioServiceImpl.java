package com.finanzas.service.impl;

import com.finanzas.dao.UsuarioDao;
import com.finanzas.domain.Usuario;
import com.finanzas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
