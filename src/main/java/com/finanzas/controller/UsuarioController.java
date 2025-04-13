package com.finanzas.controller;

import com.finanzas.domain.Usuario;
import com.finanzas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
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
    public String registrarUsuario(Usuario usuario, Model model) {
        usuarioService.registrarUsuario(usuario);
        model.addAttribute("mensaje", "Usuario registrado con éxito");
        return "redirect:/";
    }

    @PostMapping("/autenticar")
    public String autenticarUsuario(@RequestParam String correo,
                                    @RequestParam String contrasena,
                                    HttpSession session,
                                    Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.autenticarUsuario(correo, contrasena);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Validar si el usauirio esta activo o no
            if (!usuario.isActivo()) {
                model.addAttribute("error", "Usuario inactivo. Contacta al administrador.");
                return "index";
            }

            if (correo.equals("admin@admin.com") && contrasena.equals("admin")) {
                session.setAttribute("admin", usuario);
                return "redirect:/admin";
            } else {
                session.setAttribute("usuario", usuario);
                return "redirect:/dashboard";
            }

        } else {
            model.addAttribute("error", "Credenciales incorrectas. Inténtalo de nuevo.");
            return "index";
        }
    }

    @PostMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
