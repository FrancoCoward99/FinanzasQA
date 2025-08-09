package com.finanzas.controller;

import com.finanzas.domain.HistorialTransaccion;
import com.finanzas.domain.Usuario;
import com.finanzas.service.HistorialTransaccionService;
import com.finanzas.service.IngresoService;
import com.finanzas.service.GastoService;
import com.finanzas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private GastoService gastoService;

    @Autowired
    private HistorialTransaccionService historialTransaccionService;

    @GetMapping
    public String mostrarDashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        List<HistorialTransaccion> historialTransacciones = historialTransaccionService.obtenerHistorialPorUsuario(usuario.getIdUsuario());

        Double totalIngresos = ingresoService.obtenerTotalIngresos(usuario.getIdUsuario());
        Double totalGastos = gastoService.obtenerTotalGastos(usuario.getIdUsuario());
        Double saldoTotal = usuarioService.obtenerSaldoTotal(usuario.getIdUsuario());

        model.addAttribute("usuario", usuario);
        model.addAttribute("totalIngresos", totalIngresos);
        model.addAttribute("totalGastos", totalGastos);
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("historialTransacciones", historialTransacciones);

        return "dashboard";
    }

}
