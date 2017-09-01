package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.Inventario;
import com.cbj.almacen.domain.Inventarioview;
import com.cbj.almacen.repository.InventarioDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RICHARD on 17/05/2014.
 */
@Repository(value = "inventarioDao")
public class InventarioDaoImpl implements InventarioDao {

    private static final Logger logger = LoggerFactory
            .getLogger(InventarioDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public boolean insertInventario(Inventario inventario) {
        boolean respuesta=false;
        logger.trace(inventario.toString());
        try{
            logger.info(inventario.toString());
            em.persist(inventario);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;

    }

    @Transactional(readOnly = false)
    public boolean updateInventario(Inventario inventario) {
        boolean respuesta=false;
        logger.trace(inventario.toString());
        try{
            logger.info(inventario.toString());
            em.merge(inventario);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean deleteInventario(int id) {
        boolean respuesta=false;

        try{
            Inventario inventario = findInventario(id);
            em.remove(em.contains(inventario) ? inventario : em.merge(inventario));
            respuesta=true;
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;

    }

    @Override
    public Inventario findInventario(int id) {
        Inventario inventario = new Inventario();
        try{
            inventario= em.find(Inventario.class,id);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return inventario;

    }

    @Override
    public List<Inventario> findInventarioSalidas(String consecutivo, String idCliente, String descripcion, String caducidad) {
        List<Inventario> inventario = null;
        try {
            inventario = em.createQuery("select p from Inventario p WHERE p.cantidadInventario > 0 and p.consecutivo like '" + consecutivo + "%' AND p.idCliente like '" + idCliente + "%' AND p.claveProducto like '" + descripcion + "%' AND p.caducidad like '" + caducidad + "%'").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        //List<DetallesRd> detalle = em.createQuery("select p from DetallesRd p WHERE consecutivo like '"+ consecutivo + "%' AND idCliente like '"+ idCliente +"%' AND descripcion like '"+ descripcion +"%' AND caducidad like '"+ caducidad +"%'").getResultList();
        return inventario;
    }

    @Override
    public List<Inventarioview> findInventarioReporte(String consecutivo, String camara, String idCliente, String claveProducto, String tunel) {
        List<Inventarioview> inventario = null;
        try {
            String query = "select p from Inventarioview p WHERE 1 = 1 ";
                    if(!consecutivo.trim().equalsIgnoreCase("")) query+="AND p.consecutivo = '" + consecutivo + "' ";
            if(!idCliente.trim().equalsIgnoreCase("")) query+="AND p.idCliente = '" + idCliente + "' ";
            if(!camara.trim().equalsIgnoreCase("")) query+="AND p.camara = '" + camara+ "' ";
            if(!claveProducto.trim().equalsIgnoreCase("")) query+="AND p.claveProducto like '" + claveProducto + "%'";
            if(!tunel.trim().equalsIgnoreCase("")) query+="AND p.camara = '" + tunel + "' ";
            query+="AND p.cantidadInventario>0";
            inventario = em.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        //List<DetallesRd> detalle = em.createQuery("select p from DetallesRd p WHERE consecutivo like '"+ consecutivo + "%' AND idCliente like '"+ idCliente +"%' AND descripcion like '"+ descripcion +"%' AND caducidad like '"+ caducidad +"%'").getResultList();
        return inventario;
    }

    @Override
    public List<Integer> findInventarioReporteRD(String consecutivo, String camara, String idCliente, String claveProducto, String tunel) {
        List<Integer> inventario = null;
        try {
            String query = "select sum((p.pesou*p.cantidadInventario)1000) from Inventarioview p WHERE 1 = 1 ";
            if(!consecutivo.trim().equalsIgnoreCase("")) query+="AND p.consecutivo = '" + consecutivo + "' ";
            if(!idCliente.trim().equalsIgnoreCase("")) query+="AND p.idCliente = '" + idCliente + "' ";
            if(!camara.trim().equalsIgnoreCase("")) query+="AND p.camara = '" + camara+ "' ";
            if(!claveProducto.trim().equalsIgnoreCase("")) query+="AND p.claveProducto like '" + claveProducto + "%'";
            if(!tunel.trim().equalsIgnoreCase("")) query+="AND p.camara = '" + tunel + "' ";
            query+="AND p.cantidadInventario>0";
            inventario = em.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        //List<DetallesRd> detalle = em.createQuery("select p from DetallesRd p WHERE consecutivo like '"+ consecutivo + "%' AND idCliente like '"+ idCliente +"%' AND descripcion like '"+ descripcion +"%' AND caducidad like '"+ caducidad +"%'").getResultList();
        return inventario;
    }

    @Override
    public Inventario findByClienteConsecutivo(String idCliente, int consecutivo) {
        Inventario inventario = new Inventario();
        try {
        Query query = em.createNamedQuery("Inventario.getByClienteConsecutivo");
        query.setParameter("idCliente", idCliente);
        query.setParameter("consecutivo", consecutivo);
        query.setFirstResult(0);
        query.setMaxResults(1);
        inventario = (Inventario) query.getSingleResult();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return inventario;
    }

    @Override
    public Inventario findByClienteFolioSalida(String idCliente, int folioSalida) {
        return null;
    }

    @Override
    public List<Inventario> findByConsecutivo(int consecutivo) {
        List<Inventario> inventarios = new ArrayList<Inventario>();
        try {
            Query query = em.createNamedQuery("Inventario.getByConsecutivo");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return inventarios;
        }
    }
    
    @Override
    public List<Inventario> getArrastreSaldosInventarioAgrupado(int consecutivo) {
        try {
            final Query query = em.createNamedQuery("Inventario.getArrastreSaldosInventarioAgrupado");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Inventario>();
        }
    }

    @Override
    public List<Object[]> getSaldoXCamara() {
        try {
            final Query query = em.createNamedQuery("Inventario.getSaldoXCamara");
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getSaldoXTunel() {
        try {
            final Query query = em.createNamedQuery("Inventario.getSaldoXTunel");
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getSaldoXCliente() {
        try {
            final Query query = em.createNamedQuery("Inventario.getSaldoXCliente");
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getTodasCamaras() {
        try {
            final Query query = em.createNamedQuery("Inventario.getTodasCamaras");
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

}
