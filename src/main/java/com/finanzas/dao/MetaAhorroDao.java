/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.finanzas.dao;


import com.finanzas.domain.MetaAhorro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaAhorroDao extends JpaRepository<MetaAhorro, Long> {
    List<MetaAhorro> findByUsuarioIdUsuario(Long idUsuario);
}
