/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finanzas.controller;

import com.finanzas.domain.Ingreso;
import com.finanzas.service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingreso")
public class IngresoController {
    
    
    @Autowired
    private IngresoService ingresoService;
    
    @GetMapping
    public String listado(Model model) {
        
        var lista = ingresoService.getIngresos();
        
        model.addAttribute("ingresos", lista); 
        model.addAttribute("totalIngresos", lista.size());
        
        return "ingreso"; 
    }
    
    @PostMapping("/guardar")
    public String ingresoGuardar(Ingreso ingreso) {  
        ingresoService.save(ingreso);
        return "redirect:/ingreso";
    }
}