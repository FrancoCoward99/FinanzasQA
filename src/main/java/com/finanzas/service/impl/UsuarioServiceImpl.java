package com.finanzas.service.impl;

import com.finanzas.dao.UsuarioDao;
import com.finanzas.domain.Usuario;
import com.finanzas.service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional
    public void registrarUsuario(Usuario usuario) {
        usuarioDao.save(usuario); // Si se requiere también como SP, se puede migrar
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> autenticarUsuario(String correo, String contrasena) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("validar_usuario_login", Usuario.class)
                .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_contrasena", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_correo", correo);
        query.setParameter("p_contrasena", contrasena);
        query.execute();

        List<Usuario> resultado = query.getResultList();
        return resultado.isEmpty() ? Optional.empty() : Optional.of(resultado.get(0));
    }

    @Override
    @Transactional(readOnly = true)
    public Double obtenerSaldoTotal(Long idUsuario) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("obtener_saldo_total")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Double.class, ParameterMode.OUT);

        query.setParameter(1, idUsuario);
        query.execute();

        Double saldo = (Double) query.getOutputParameterValue(2);
        return saldo != null ? saldo : 0.0;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerUsuarioPorId(Long idUsuario) {
        return usuarioDao.findById(idUsuario);
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional
    public void eliminarUsuarioPorId(Long idUsuario) {
        usuarioDao.deleteById(idUsuario);
    }

    @Override
    @Transactional
    public void cambiarEstadoUsuario(Long idUsuario) {
        Optional<Usuario> usuarioOpt = usuarioDao.findById(idUsuario);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setActivo(!usuario.isActivo());
            usuarioDao.save(usuario);
        }
    }
}
