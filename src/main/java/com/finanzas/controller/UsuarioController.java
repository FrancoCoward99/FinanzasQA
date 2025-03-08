package com.finanzas.controller;

import com.finanzas.domain.Usuario;
import com.finanzas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index"; // Página de inicio de sesión
    }

    @PostMapping("/autenticar")
    public String autenticarUsuario(@RequestParam String correo, @RequestParam String contrasena, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.autenticarUsuario(correo, contrasena);

        if (usuarioOpt.isPresent()) {
            model.addAttribute("usuario", usuarioOpt.get());
            return "dashboard"; // Redirige al Dashboard si las credenciales son correctas
        } else {
            model.addAttribute("error", "Credenciales incorrectas. Inténtalo de nuevo.");
            return "index"; // Devuelve a la pantalla de login con error
        }
    }
}
