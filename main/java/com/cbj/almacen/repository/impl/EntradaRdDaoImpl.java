package com.cbj.almacen.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.repository.DetalleRdDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by RICHARD on 17/05/2014.
 */
@Repository(value = "detalleRdDao")
public class EntradaRdDaoImpl implements DetalleRdDao {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradaRdDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public boolean insertRegistroDetallesRd(DetallesRd detalles) {
        boolean respuesta = false;
        logger.trace(detalles.toString());
        try {
            logger.info(detalles.toString());
            em.persist(detalles);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateRegistroDetallesRd(DetallesRd detalles) {
        boolean respuesta = false;
        logger.trace(detalles.toString());
        try {
            logger.info(detalles.toString());
            em.merge(detalles);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean deleteRegistroDetallesRd(int id) {
        boolean respuesta = false;

        try {
            DetallesRd detallesRd = findRegistroDetallesRd(id);
            em.remove(em.contains(detallesRd) ? detallesRd : em.merge(detallesRd));
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    public DetallesRd findRegistroDetallesRd(int id) {
        DetallesRd detallesRd = new DetallesRd();
        try {
            detallesRd = em.find(DetallesRd.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        return detallesRd;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<DetallesRd> findDetallesSalidas(String consecutivo, String idCliente, String descripcion, String caducidad) {
        List<DetallesRd> detalle = null;
        try {
            detalle = em.createQuery("select p from DetallesRd p WHERE p.consecutivo like '" + consecutivo + "%' AND p.idCliente like '" + idCliente + "%' AND p.claveProducto like '" + descripcion + "%' AND p.caducidad like '" + caducidad + "%'").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        //List<DetallesRd> detalle = em.createQuery("select p from DetallesRd p WHERE consecutivo like '"+ consecutivo + "%' AND idCliente like '"+ idCliente +"%' AND descripcion like '"+ descripcion +"%' AND caducidad like '"+ caducidad +"%'").getResultList();
        return detalle;
    }

    @Override
    public List<DetallesRd> getAll() {
        Query query = em.createNamedQuery("DetallesRd.getAll");
        return query.getResultList();

    }

    @Override
    public List<DetallesRd> getAllByConsecutivo(int consecutivo) {
            Query query = em.createNamedQuery("DetallesRd.getAllByConsecutivo");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
    }
    
    @Override
    public List<DetallesRd> getAllByCliente(String idCliente) {
            Query query = em.createNamedQuery("DetallesRd.getAllByCliente");
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
    }
    
    @Override
    public List<DetallesRd> getAllByFolioSalida(int folioSalida, int consecutivo, String idCliente) {
            Query query = em.createNamedQuery("DetallesRd.getAllByFolioSalida");
            query.setParameter("folioSalida", folioSalida);
            query.setParameter("consecutivo", consecutivo);
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
    }

    @Override
    public List<Object[]> getKilosByDay(Date fechaSalida) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getKilosByDay");
            query.setParameter("fechaSalida", fechaSalida, TemporalType.DATE);
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }


    @Override
    public List<Object[]> getKilosByFechas(Date fechaInicio, Date fechaFin) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getKilosByFechas");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getKilosByFechasCliente(Date fechaInicio, Date fechaFin, String idCliente) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getKilosByFechasCliente");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getKilosByFechasClienteDetalle(final Date fechaInicio, final Date fechaFin, final String idCliente) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getKilosByFechasClienteDetalle");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public Object getKilosByFechasClienteDetalleTotal(final Date fechaInicio, final Date fechaFin, final String idCliente) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getKilosByFechasClienteDetalleTotal");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("idCliente", idCliente);
            return (Object) query.getSingleResult();
        }catch (NoResultException e){
                return new Object();
        }
    }

    @Override
    public List<Object[]> getReporteByCamara(Date fechaInicio, Date fechaFin, String idCamara) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getReporteByCamara");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("idCamara", idCamara);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getReporteByCamaraVacia(Date fechaInicio, Date fechaFin) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getReporteByCamaraVacia");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }
    
    @Override
    public List<DetallesRd> getArrastreSaldosDetalleRdAgrupado(int consecutivo) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getArrastreSaldosDetalleRdAgrupado");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<DetallesRd>();
        }
    }

}
