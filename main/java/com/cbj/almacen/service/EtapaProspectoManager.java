package com.cbj.almacen.service;


import com.cbj.almacen.domain.EtapaProspecto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RICHARD on 01/09/2014.
 */
public interface EtapaProspectoManager extends Serializable {

   
    public String getEtapaNombre(int id_etapa);
    
}
