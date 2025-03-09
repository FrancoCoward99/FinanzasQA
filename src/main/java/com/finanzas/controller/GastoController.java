/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finanzas.controller;

import com.finanzas.domain.Gasto;
import com.finanzas.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gasto")
public class GastoController {
    
    
    @Autowired
    private GastoService gastoService;
    
    @GetMapping
    public String listado(Model model) {
        
        var lista = gastoService.getGastos();
        
        model.addAttribute("gastos", lista); 
        model.addAttribute("totalGastos", lista.size());
        
        return "gasto"; 
    }
    
    @PostMapping("/guardar")
    public String gastoGuardar(Gasto gasto) {  
        gastoService.save(gasto);
        return "redirect:/gasto";
    }
}