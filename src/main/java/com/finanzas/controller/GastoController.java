package com.finanzas.controller;

import com.finanzas.domain.Categoria;
import com.finanzas.domain.Gasto;
import com.finanzas.domain.Tarjeta;
import com.finanzas.domain.Usuario;
import com.finanzas.service.CategoriaService;
import com.finanzas.service.GastoService;
import com.finanzas.service.IngresoService;
import com.finanzas.service.TarjetaService;
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

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private IngresoService ingresoService;

    @GetMapping
    public String mostrarGastos(HttpSession session, Model model,
                                @RequestParam(value = "alertaNegativo", required = false) String alertaNegativo) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/";
        }

        List<Gasto> gastos = gastoService.obtenerGastosPorUsuario(usuario.getIdUsuario());

        // ✅ CORREGIDO: Solo categorías de tipo GASTO
        List<Categoria> categorias = categoriaService.obtenerCategoriasPorTipo(usuario.getIdUsuario(), "GASTO");
        List<Tarjeta> tarjetas = tarjetaService.obtenerTarjetasPorUsuario(usuario.getIdUsuario());

        model.addAttribute("usuario", usuario);
        model.addAttribute("gastos", gastos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("tarjetas", tarjetas);
        model.addAttribute("totalGastos", gastos.size());

        if ("true".equals(alertaNegativo)) {
            model.addAttribute("alertaNegativo", true);
        }

        return "gasto";
    }

    @PostMapping("/guardar")
    public String guardarGasto(@RequestParam("fecha") String fecha,
                               @RequestParam("descripcion") String descripcion,
                               @RequestParam("monto") Double monto,
                               @RequestParam("categoria") Long idCategoria,
                               @RequestParam("tarjeta") Long idTarjeta,
                               HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/";
        }

        Double totalIngresos = ingresoService.obtenerTotalIngresos(usuario.getIdUsuario());
        Double totalGastos = gastoService.obtenerTotalGastos(usuario.getIdUsuario());

        if (totalIngresos - (totalGastos + monto) < 0) {
            return "redirect:/gasto?alertaNegativo=true";
        }

        Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria).orElse(null);
        Tarjeta tarjeta = tarjetaService.obtenerTarjetaPorId(idTarjeta).orElse(null);

        if (categoria == null || tarjeta == null) {
            return "redirect:/error";
        }

        Gasto gasto = new Gasto();
        gasto.setUsuario(usuario);
        gasto.setCategoria(categoria);
        gasto.setTarjeta(tarjeta);
        gasto.setDescripcion(descripcion);
        gasto.setMonto(monto);
        gasto.setFecha(java.sql.Date.valueOf(fecha));

        gastoService.save(gasto);

        return "redirect:/gasto";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarGasto(@PathVariable("id") Long id) {
        gastoService.eliminarPorId(id);
        return "redirect:/gasto";
    }

    @GetMapping("/categoria/{idCategoria}")
    public String gastosPorCategoria(@PathVariable Long idCategoria, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/";
        }

        List<Gasto> lista;
        if (idCategoria == 0) {
            lista = gastoService.obtenerGastosPorUsuario(usuario.getIdUsuario());
        } else {
            lista = gastoService.obtenerGastosPorCategoria(idCategoria, usuario.getIdUsuario());
        }

        // ✅ CORREGIDO: Solo categorías tipo GASTO
        List<Categoria> categorias = categoriaService.obtenerCategoriasPorTipo(usuario.getIdUsuario(), "GASTO");
        List<Tarjeta> tarjetas = tarjetaService.obtenerTarjetasPorUsuario(usuario.getIdUsuario());

        model.addAttribute("usuario", usuario);
        model.addAttribute("gastos", lista);
        model.addAttribute("categorias", categorias);
        model.addAttribute("tarjetas", tarjetas);
        model.addAttribute("totalGastos", lista.size());

        return "gasto";
    }
}
