package com.finanzas.controller;

import com.finanzas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String mostrarPanelAdmin(HttpSession session, Model model) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/";
        }

        model.addAttribute("usuarios", usuarioService.obtenerTodosLosUsuarios());
        return "admin";
    }

    @GetMapping("/toggle-estado/{idUsuario}")
    public String cambiarEstado(@PathVariable Long idUsuario, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/";
        }

        usuarioService.cambiarEstadoUsuario(idUsuario);
        return "redirect:/admin";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String eliminarUsuario(@PathVariable Long idUsuario, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/";
        }

        usuarioService.eliminarUsuarioPorId(idUsuario);
        return "redirect:/admin";
    }

    @PostMapping("/logout")
    public String cerrarSesionAdmin(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/";
    }
}
