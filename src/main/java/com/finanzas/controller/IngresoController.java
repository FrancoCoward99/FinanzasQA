package com.finanzas.controller;

import com.finanzas.domain.Ingreso;
import com.finanzas.domain.Categoria;
import com.finanzas.domain.Usuario;
import com.finanzas.service.IngresoService;
import com.finanzas.service.CategoriaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ingreso")
public class IngresoController {

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listado(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        var lista = ingresoService.getIngresosPorUsuario(usuario.getIdUsuario());
        List<Categoria> categorias = categoriaService.obtenerCategoriasPorUsuario(usuario.getIdUsuario());

        model.addAttribute("usuario", usuario);
        model.addAttribute("ingresos", lista);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalIngresos", lista.size());

        return "ingreso";
    }

    //se realiza los cambios para incorporar los filtros por categoria AYUDAaaaaaaaaa
//    @GetMapping("/listadoTodo")
//    public String listadoTodo(HttpSession session, Model model) {
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//
//        if (usuario == null) {
//            return "redirect:/usuario/login";
//        }
//
//        var lista = ingresoService.getIngresosPorUsuario(usuario.getIdUsuario());
//        List<Categoria> categorias = categoriaService.obtenerCategoriasPorUsuario(usuario.getIdUsuario());
//
//        model.addAttribute("usuario", usuario);
//        model.addAttribute("ingresos", lista);
//        model.addAttribute("categorias", categorias);
//        model.addAttribute("totalIngresos", lista.size());
//
//        return "ingreso";
//    }
//
//    @GetMapping("/listado/{idCategoria")
//    public String listado(HttpSession session, Model model, Categoria categoria) {
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//
//        if (usuario == null) {
//            return "redirect:/usuario/login";
//        }
//
//        var lista = ingresoService.getIngresosPorUsuario(usuario.getIdUsuario());
//        List<Categoria> categorias = categoriaService.obtenerCategoriasPorUsuario(usuario.getIdUsuario());
//
//        Categoria categoriaSeleccionada = null;
//        for (Categoria categoria : categorias) {
//            if (categoria.getIdCategoria().equals(idCategoria)) { 
//                categoriaSeleccionada = categoria;
//                break;
//            }
//        }
//
//        
//        List<Ingreso> ingresos = null;
//        if (categoriaSeleccionada != null) {
//            ingresos = categoriaSeleccionada.getIngresos();  
//        }
//        model.addAttribute("usuario", usuario);
//        model.addAttribute("ingresos", lista);
//        model.addAttribute("categorias", categorias);
//        model.addAttribute("totalIngresos", lista.size());
//
//        return "ingreso";
//    }

    //-----------------------------------------------------------------------------------------------------------------
    @PostMapping("/guardar")
    public String ingresoGuardar(@RequestParam("categoria") Long idCategoria,
            @ModelAttribute Ingreso ingreso,
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

        ingreso.setUsuario(usuario);
        ingreso.setCategoria(categoria);
        ingresoService.save(ingreso);

        return "redirect:/ingreso";
    }

    @PostMapping("/eliminar/{idIngreso}")
    public String eliminarIngreso(@PathVariable Long idIngreso) {
        ingresoService.eliminarIngreso(idIngreso);
        return "redirect:/ingreso"; // Redirige a la lista de ingresos
    }

}
