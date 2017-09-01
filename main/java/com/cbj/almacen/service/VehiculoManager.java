package com.cbj.almacen.service;

import com.cbj.almacen.domain.Vehiculo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public interface VehiculoManager extends Serializable {

    public boolean setIngresaVehiculo(Vehiculo vehiculo);
    public Vehiculo getIngresaVehiculoById(Integer id);
    public boolean updateIngresaVehiculo(Vehiculo vehiculo);
    public List<Vehiculo> getVehiculosPorSalir();
    public List<Vehiculo> getVehiculoByCliente(int idCliente);
    public List<Vehiculo> getInOutVehiculo(Date fecini, Date fecfin);
}