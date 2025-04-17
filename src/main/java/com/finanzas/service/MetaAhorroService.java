/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.finanzas.service;


import com.finanzas.domain.MetaAhorro;
import java.util.List;

public interface MetaAhorroService {
    List<MetaAhorro> obtenerMetasPorUsuario(Long idUsuario);
    void guardarMeta(MetaAhorro meta);
    void eliminarMeta(Long idMeta);
}