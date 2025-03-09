package com.finanzas.controller;

import com.finanzas.domain.Categoria;
import com.finanzas.domain.Usuario;
import com.finanzas.service.CategoriaService;
import com.finanzas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar formulario para crear una nueva categoría
    @GetMapping("/nueva")
    public String mostrarFormularioCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formularioCategoria"; // Vista donde se llena el formulario
    }

    // Guardar nueva categoría
    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria, @RequestParam Long idUsuario, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorId(idUsuario);

        if (usuarioOpt.isPresent()) {
            categoria.setUsuario(usuarioOpt.get());
            categoriaService.guardarCategoria(categoria);
            return "redirect:/categoria/lista"; // Redirige a la lista de categorías
        } else {
            model.addAttribute("error", "Usuario no encontrado.");
            return "formularioCategoria";
        }
    }

    // Mostrar lista de categorías de un usuario
    @GetMapping("/lista")
    public String listarCategorias(@RequestParam Long idUsuario, Model model) {
        List<Categoria> categorias = categoriaService.obtenerCategoriasPorUsuario(idUsuario);
        model.addAttribute("categorias", categorias);
        return "listaCategorias"; // Vista donde se muestran las categorías
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Categoria> categoriaOpt = categoriaService.obtenerCategoriaPorId(id);
        
        if (categoriaOpt.isPresent()) {
            model.addAttribute("categoria", categoriaOpt.get());
            return "formularioCategoria"; // Reutiliza la vista de formulario
        } else {
            return "redirect:/categoria/lista";
        }
    }

    // Eliminar categoría
    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categoria/lista";
    }
}
