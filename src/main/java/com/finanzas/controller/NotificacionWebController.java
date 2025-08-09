package com.finanzas.controller;

import com.finanzas.domain.Notificacion;
import com.finanzas.domain.Usuario;
import com.finanzas.service.NotificacionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NotificacionWebController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/notificaciones")
    public String verNotificaciones(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/error";

        List<Notificacion> notificaciones = notificacionService.obtenerNotificaciones(usuario.getIdUsuario());
        model.addAttribute("notificaciones", notificaciones);
        return "Notificacion";
    }
}
