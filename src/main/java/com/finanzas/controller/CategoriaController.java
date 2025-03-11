package com.finanzas.controller;
 
import com.finanzas.domain.Categoria;
import com.finanzas.domain.TipoCategoria;
import com.finanzas.domain.Usuario;
import com.finanzas.service.CategoriaService;
import com.finanzas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
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
 
    // Mostrar lista de categorías del usuario autenticado
    @GetMapping("/lista")
    public String listarCategorias(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
 
        if (usuario == null) {
            return "redirect:/error"; // Si no hay usuario, redirigir a error
        }
 
        System.out.println("✅ Usuario autenticado: " + usuario.getNombre());
 
        List<Categoria> categorias = categoriaService.obtenerCategoriasPorUsuario(usuario.getIdUsuario());
        model.addAttribute("usuario", usuario);
        model.addAttribute("categorias", categorias);
 
        return "categoria"; // Renderiza categoria.html en templates/
    }
 
    // Guardar una nueva categoría
    @PostMapping("/guardar")
    public String guardarCategoria(@RequestParam String nombre, @RequestParam String tipo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
 
        if (usuario == null) {
            return "redirect:/usuario/login";
        }
 
        Categoria categoria = new Categoria(usuario, nombre, TipoCategoria.valueOf(tipo));
        categoriaService.guardarCategoria(categoria);
 
        return "redirect:/categoria/lista";
    }
 
    // Eliminar categoría por ID
    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
 
        if (usuario == null) {
            return "redirect:/usuario/login"; // Redirigir si no hay usuario en sesión
        }
 
        Optional<Categoria> categoriaOpt = categoriaService.obtenerCategoriaPorId(id);
 
        if (categoriaOpt.isPresent()) {
            Categoria categoria = categoriaOpt.get();
 
            if (categoria.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
                categoriaService.eliminarCategoria(id);
            } else {
                return "redirect:/categoria/lista?error=No tienes permiso para eliminar esta categoría";
            }
        } else {
            return "redirect:/categoria/lista?error=Categoría no encontrada";
        }
 
        return "redirect:/categoria/lista";
    }
}