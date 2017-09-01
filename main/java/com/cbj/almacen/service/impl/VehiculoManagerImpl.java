package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Vehiculo;
import com.cbj.almacen.repository.VehiculoDao;
import com.cbj.almacen.service.VehiculoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class VehiculoManagerImpl implements VehiculoManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private VehiculoDao vehiculoDao;
    
    
    public boolean setIngresaVehiculo(Vehiculo vehiculo) {
    	return vehiculoDao.insertVehiculo(vehiculo);
        }

    public Vehiculo getIngresaVehiculoById(Integer id) {
    	return vehiculoDao.findVehiculoById(id);
        }

    public boolean updateIngresaVehiculo(Vehiculo vehiculo) {
    	return vehiculoDao.updateVehiculo(vehiculo);
        }

    @Override
    public List<Vehiculo> getVehiculosPorSalir() {
        return vehiculoDao.findVehiculosPorSalir();
    }
    
    public List<Vehiculo> getVehiculoByCliente(int idCliente) {
        return vehiculoDao.getVehiculoByCliente(idCliente);
    }
    
    public List<Vehiculo> getInOutVehiculo(Date fecini,Date fecfin) {
        return vehiculoDao.getInOutVehiculo(fecini,fecfin);
    }
    

}
