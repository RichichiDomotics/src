package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Vehiculo;
import com.cbj.almacen.repository.VehiculoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository(value = "vehiculoDao")
public class VehiculoDaoImpl implements VehiculoDao {

    private static final Logger logger = LoggerFactory
            .getLogger(VehiculoDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
	this.em = em;
    }

   
    @Transactional(readOnly = false)
    public boolean insertVehiculo(Vehiculo datosIngresoVehiculo){
	boolean respuesta=false;
    try{
    	em.persist(datosIngresoVehiculo);
    	respuesta=true;
	}catch(Exception e){
		respuesta=false;
    }
	return respuesta;
    }
    
    @Override
    public Vehiculo findVehiculoById(Integer id) {
    	Vehiculo vehiculo = new Vehiculo();
        try{
        	vehiculo= em.find(Vehiculo.class,id);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return vehiculo;

    }
    
    @Transactional(readOnly = false)
    public boolean updateVehiculo(Vehiculo datosIngresoVehiculo) {
        boolean respuesta=false;
        logger.trace(datosIngresoVehiculo.toString());
        try{
            logger.info(datosIngresoVehiculo.toString());
            em.merge(datosIngresoVehiculo);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Override
    public List<Vehiculo> findVehiculosPorSalir() {
        try{
            Query query = em.createNamedQuery("Vehiculo.getPorSalir");
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Vehiculo>();
        }
    }
    
    @Override
    public List<Vehiculo> getVehiculoByCliente(int idCliente) {
            Query query = em.createNamedQuery("Vehiculo.getVehiculoByCliente");
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
    }
    
    @Override
    public List<Vehiculo> getInOutVehiculo(Date fecini, Date fecfin) {
            Query query = em.createNamedQuery("Vehiculo.getInOutVehiculo");
        query.setParameter("fecini", fecini, TemporalType.DATE);
        query.setParameter("fecfin", fecfin, TemporalType.DATE);
            return query.getResultList();
    }
    

}
