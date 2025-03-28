package com.finanzas.controller;

import com.finanzas.domain.Configuracion;
import com.finanzas.service.ConfiguracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ConfiguracionController {

    @Autowired
    private ConfiguracionService configuracionService;

    @GetMapping("/configuracion")
    public String mostrarFormularioConfiguracion(@RequestParam Long idUsuario, Model model) {
        Optional<Configuracion> configuracionOpt = configuracionService.obtenerConfiguracionPorId(idUsuario);
        if (configuracionOpt.isPresent()) {
            model.addAttribute("configuracion", configuracionOpt.get());
            return "configuracion";  // Nombre del archivo HTML: configuracion.html
        }
        return "redirect:/dashboard";  // Si no existe, redirige al dashboard
    }

    // Procesar el formulario de configuración del usuario
    @PostMapping("/configuracion")
    public String actualizarConfiguracion(Configuracion configuracion) {
        Optional<Configuracion> configuracionOpt = configuracionService.obtenerConfiguracionPorId(configuracion.getIdUsuario());
        if (configuracionOpt.isPresent()) {
            Configuracion usuarioExistente = configuracionOpt.get();

            // Validar el campo nombre (no puede ser vacío o solo espacios)
            if (configuracion.getNombre() != null && !configuracion.getNombre().trim().isEmpty()) {
                usuarioExistente.setNombre(configuracion.getNombre());  // Solo actualizar si no está vacío
            }

            // Validar la contraseña (solo actualizar si no está vacía o solo tiene espacios)
            if (configuracion.getContrasena() != null && !configuracion.getContrasena().trim().isEmpty()) {
                usuarioExistente.setContrasena(configuracion.getContrasena());  // Solo actualizar si no está vacío
            }

            // Guardar los cambios
            configuracionService.actualizarConfiguracion(usuarioExistente);

            // Redirigir al dashboard o a la página de éxito
            return "redirect:/dashboard";  // O bien, redirigir a otra página de éxito
        }

        // Si el usuario no existe, redirigir a otra página
        return "redirect:/dashboard";
    }
}
