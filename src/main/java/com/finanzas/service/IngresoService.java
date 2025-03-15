
package com.finanzas.service;

import com.finanzas.domain.Ingreso;
import java.util.List;


public interface IngresoService {

    public List<Ingreso> getIngresos();
    
    public Ingreso getIngreso(Ingreso ingreso);
    
    public void save(Ingreso ingreso);
    
    List<Ingreso> getIngresosPorUsuario(Long idUsuario);
    

}
