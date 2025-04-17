package com.finanzas.controller;

import com.finanzas.domain.MetaAhorro;
import com.finanzas.domain.Usuario;
import com.finanzas.service.MetaAhorroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ahorro")
public class AhorroController {

    @Autowired
    private MetaAhorroService metaAhorroService;

    @GetMapping
    public String mostrarMetas(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/";
        }

        List<MetaAhorro> metas = metaAhorroService.obtenerMetasPorUsuario(usuario.getIdUsuario());

        model.addAttribute("usuario", usuario);
        model.addAttribute("metas", metas);

        return "ahorro";
    }

    @PostMapping("/guardar")
    public String guardarMeta(HttpSession session,
                              @RequestParam String nombre,
                              @RequestParam Double montoMensual,
                              @RequestParam int meses) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/";
        }

        MetaAhorro meta = new MetaAhorro();
        meta.setNombre(nombre);
        meta.setMontoObjetivo(montoMensual);
        meta.setFechaLimite(Date.valueOf(LocalDate.now().plusMonths(meses)));
        meta.setUsuario(usuario);
        meta.setMeses(meses);

        metaAhorroService.guardarMeta(meta);
        return "redirect:/ahorro";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarMeta(@PathVariable("id") Long id) {
        metaAhorroService.eliminarMeta(id);
        return "redirect:/ahorro";
    }
}