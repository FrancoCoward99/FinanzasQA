
package com.finanzas.service;

import com.finanzas.domain.Gasto;
import java.util.List;


public interface GastoService {

    public List<Gasto> getGastos();
    
    public Gasto getGasto(Gasto gasto);
    
    public void save(Gasto gasto);
    
    List<Gasto> getGastosPorUsuario(Long idUsuario);
    

}
