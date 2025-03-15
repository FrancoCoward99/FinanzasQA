package com.finanzas.controller;

import com.finanzas.domain.Categoria;
import com.finanzas.domain.Gasto;
import com.finanzas.domain.Usuario;
import com.finanzas.service.CategoriaService;
import com.finanzas.service.GastoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gasto")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listado(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        var lista = gastoService.getGastosPorUsuario(usuario.getIdUsuario());
        List<Categoria> categorias = categoriaService.obtenerCategoriasPorUsuario(usuario.getIdUsuario());

        model.addAttribute("usuario", usuario);
        model.addAttribute("gastos", lista);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalGastos", lista.size());

        return "gasto";
    }
    
    @PostMapping("/guardar")
    public String ingresoGuardar(@RequestParam("categoria") Long idCategoria,
            @ModelAttribute Gasto gasto,
            HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        // Obtener la categoría seleccionada
        Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria).orElse(null);

        if (categoria == null) {
            return "redirect:/error";
        }

        gasto.setUsuario(usuario);
        gasto.setCategoria(categoria);
        gastoService.save(gasto);

        return "redirect:/gasto";
    }
    
}