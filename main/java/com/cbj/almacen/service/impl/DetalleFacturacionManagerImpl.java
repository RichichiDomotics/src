package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.DetalleFacturacion;
import com.cbj.almacen.repository.DetalleFacturacionDao;
import com.cbj.almacen.service.DetalleFacturacionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * Created by RICHARD on 10/06/2014.
 */
@Component
public class DetalleFacturacionManagerImpl implements DetalleFacturacionManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private DetalleFacturacionDao detalleFacturacionDao;

    @Transactional(readOnly = false)
    public DetalleFacturacion insertDetalleFacturacion(DetalleFacturacion detalleFacturacion) {
        return detalleFacturacionDao.insertDetalleFacturacion(detalleFacturacion);
    }

    @Override
    public List<Object> getIdClienteDetalle(Integer idCliente) {
        return detalleFacturacionDao.getIdClienteDetalle(idCliente);
    }

    @Override
    public List<Object> getIdDetalleFactura(String nofactura) {
        return detalleFacturacionDao.getIdDetalleFactura(nofactura);
    }

    @Override
    public List<Object> getVentasEjecutivo(String fechainicio, String fechafin){
        return detalleFacturacionDao.getVentasEjecutivo(fechainicio, fechafin);
    }

    @Override
    public List<Object> getVentasNetasEjecutivo(String fechainicio, String fechafin){
        return detalleFacturacionDao.getVentasNetasEjecutivo(fechainicio, fechafin);
    }
    @Override
    public double getVentasTotal(String fechainicio, String fechafin){
        return detalleFacturacionDao.getVentasTotal(fechainicio, fechafin);
    }
    @Override
    public double getVentasNetasEjecutivoTotal(String fechainicio, String fechafin){
        return detalleFacturacionDao.getVentasNetasEjecutivoTotal(fechainicio, fechafin);
    }
    @Override
    public List<Object> getVentasEjecutivoId(String fechainicio, String fechafin, String idEjecutivo){
        return detalleFacturacionDao.getVentasEjecutivoId(fechainicio, fechafin, idEjecutivo);
    }
    @Override
    public List<Object> getVentasNetasEjecutivoId(String fechainicio, String fechafin, String idEjecutivo){
        return detalleFacturacionDao.getVentasNetasEjecutivoId(fechainicio, fechafin, idEjecutivo);
    }
    @Override
    public double getVentasNetasEjecutivoTotalID(String fechainicio, String fechafin, String idEjecutivo){
        return detalleFacturacionDao.getVentasNetasEjecutivoTotalID(fechainicio, fechafin, idEjecutivo);
    }
}
