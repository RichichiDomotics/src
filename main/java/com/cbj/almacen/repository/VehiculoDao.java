package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Vehiculo;

import java.util.Date;
import java.util.List;


public interface VehiculoDao {

    public boolean insertVehiculo(Vehiculo datosIngresoVehiculo);
    public Vehiculo findVehiculoById(Integer idVehiculo);
    public boolean updateVehiculo(Vehiculo datosIngresoVehiculo);
    public List<Vehiculo> findVehiculosPorSalir();
    public List<Vehiculo> getVehiculoByCliente(int idCliente);
    public List<Vehiculo> getInOutVehiculo(Date fecini, Date fecfin);
}
