package com.finanzas.controller;

import com.finanzas.domain.Usuario;
import com.finanzas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ConfiguracionController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/configuracion")
    public String mostrarFormularioConfiguracion(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/";
        }
        model.addAttribute("usuario", usuario);
        return "configuracion";
    }

    @PostMapping("/configuracion")
    public String actualizarConfiguracion(Usuario usuarioForm, HttpSession session, Model model) {
        Usuario sessionUsuario = (Usuario) session.getAttribute("usuario");
        if (sessionUsuario == null) {
            return "redirect:/error";
        }

        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorId(sessionUsuario.getIdUsuario());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (usuarioForm.getNombre() != null && !usuarioForm.getNombre().trim().isEmpty()) {
                usuario.setNombre(usuarioForm.getNombre());
            }

            if (usuarioForm.getContrasena() != null && !usuarioForm.getContrasena().trim().isEmpty()) {
                usuario.setContrasena(usuarioForm.getContrasena());
            }

            usuarioService.actualizarUsuario(usuario);
            session.setAttribute("usuario", usuario);
        }

        return "redirect:/configuracion";
    }
}

