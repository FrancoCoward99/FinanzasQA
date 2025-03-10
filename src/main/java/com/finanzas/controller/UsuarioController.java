package com.finanzas.controller;

import com.finanzas.domain.Usuario;
import com.finanzas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registroUsuario";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            usuarioService.registrarUsuario(usuario);
            model.addAttribute("mensaje", "Usuario registrado con éxito");
            return "redirect:/"; // Redirigir al login
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar usuario: " + e.getMessage());
            return "registroUsuario";
        }
    }

    @PostMapping("/autenticar")
    public String autenticarUsuario(@RequestParam String correo, @RequestParam String contrasena,
            HttpSession session, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.autenticarUsuario(correo, contrasena);

        if (usuarioOpt.isPresent()) {
            session.setAttribute("usuario", usuarioOpt.get()); // GUARDAR USUARIO EN SESIÓN
            return "redirect:/dashboard"; // Redirigir a Dashboard
        } else {
            model.addAttribute("error", "Credenciales incorrectas. Inténtalo de nuevo.");
            return "index"; // Volver a la pantalla de login con error
        }
    }

    /*
    opcion 4

    @PostMapping("/autenticar")
    public String autenticarUsuario(@RequestParam String correo, @RequestParam String contrasena, HttpSession session, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOpt = usuarioService.autenticarUsuario(correo, contrasena);

        if (usuarioOpt.isPresent()) {
            session.setAttribute("usuario", usuarioOpt.get()); // Guardamos el usuario en sesión
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Credenciales incorrectas. Inténtalo de nuevo.");
            return "redirect:/usuario/login";
        }
    }
     */
 /*
    opcion 3
    @PostMapping("/autenticar")
    public String autenticarUsuario(@RequestParam String correo, @RequestParam String contrasena, HttpSession session, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOpt = usuarioService.autenticarUsuario(correo, contrasena);

        if (usuarioOpt.isPresent()) {
            session.setAttribute("usuario", usuarioOpt.get()); // Guarda usuario en sesión
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Credenciales incorrectas. Inténtalo de nuevo.");
            return "redirect:/usuario/login";
        }
    }
     */

 /*
    opcion 2
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index"; // Página de inicio de sesión
    }

    @PostMapping("/autenticar")
    public String autenticarUsuario(@RequestParam String correo, @RequestParam String contrasena, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOpt = usuarioService.autenticarUsuario(correo, contrasena);

        if (usuarioOpt.isPresent()) {
            redirectAttributes.addFlashAttribute("usuario", usuarioOpt.get()); // Agrega el usuario a la redirección
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Credenciales incorrectas. Inténtalo de nuevo.");
            return "redirect:/usuario/login"; // Redirige de vuelta al login
        }
    }
     */
 /*
    opcion 1
    @PostMapping("/autenticar")
    public String autenticarUsuario(@RequestParam String correo, @RequestParam String contrasena, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.autenticarUsuario(correo, contrasena);

        if (usuarioOpt.isPresent()) {
            return "redirect:/dashboard"; // Redirige correctamente a la URL de dashboard
        } else {
            model.addAttribute("error", "Credenciales incorrectas. Inténtalo de nuevo.");
            return "index"; // Devuelve a la pantalla de login con error
        }
    }
     */
}
