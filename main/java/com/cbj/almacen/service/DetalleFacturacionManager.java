package com.cbj.almacen.service;

import com.cbj.almacen.domain.DetalleFacturacion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RICHARD on 10/06/2014.
 */
public interface DetalleFacturacionManager extends Serializable {

    public DetalleFacturacion insertDetalleFacturacion(DetalleFacturacion detalleFacturacion);
    public List<Object> getIdClienteDetalle(Integer idCliente);
    public List<Object> getIdDetalleFactura(String nofactura);
    public List<Object> getVentasEjecutivo(String fechainicio, String fechafin);
    public List<Object> getVentasNetasEjecutivo(String fechainicio, String fechafin);
    public double getVentasTotal(String fechainicio, String fechafin);
    public double getVentasNetasEjecutivoTotal(String fechainicio, String fechafin);
    public List<Object> getVentasEjecutivoId(String fechainicio, String fechafin, String idEjecutivo);
    public List<Object> getVentasNetasEjecutivoId(String fechainicio, String fechafin, String idEjecutivo);
    public double getVentasNetasEjecutivoTotalID(String fechainicio, String fechafin, String idEjecutivo);
}
