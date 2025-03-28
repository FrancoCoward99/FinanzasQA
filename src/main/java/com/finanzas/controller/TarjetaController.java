package com.finanzas.controller;

import com.finanzas.domain.Tarjeta;
import com.finanzas.domain.Usuario;
import com.finanzas.service.TarjetaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tarjeta")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    // Mostrar lista de tarjetas del usuario autenticado
    @GetMapping("/lista")
    public String listarTarjetas(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/error";
        }

        List<Tarjeta> tarjetas = tarjetaService.obtenerTarjetasPorUsuario(usuario.getIdUsuario());

        model.addAttribute("usuario", usuario);
        model.addAttribute("tarjetas", tarjetas);

        return "tarjeta";
    }

    // Guardar nueva tarjeta
    @PostMapping("/guardar")
    public String guardarTarjeta(@RequestParam String nombre,
                                  @RequestParam String numeroTarjeta,
                                  @RequestParam String tipo,
                                  HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/error";
        }

        Tarjeta tarjeta = new Tarjeta(usuario, nombre, numeroTarjeta, tipo);
        tarjetaService.guardarTarjeta(tarjeta);

        return "redirect:/tarjeta/lista";
    }

    // Eliminar tarjeta por ID
    @PostMapping("/eliminar/{id}")
    public String eliminarTarjeta(@PathVariable("id") Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/error";
        }

        Optional<Tarjeta> tarjetaOpt = tarjetaService.obtenerTarjetaPorId(id);

        if (tarjetaOpt.isPresent()) {
            Tarjeta tarjeta = tarjetaOpt.get();

            if (tarjeta.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
                tarjetaService.eliminarTarjeta(id);
            } else {
                return "redirect:/tarjeta/lista?error=No tienes permiso para eliminar esta tarjeta";
            }
        } else {
            return "redirect:/tarjeta/lista?error=Tarjeta no encontrada";
        }

        return "redirect:/tarjeta/lista";
    }
}
