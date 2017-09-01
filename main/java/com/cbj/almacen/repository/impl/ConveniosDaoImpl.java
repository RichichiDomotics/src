package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Convenios;
import com.cbj.almacen.repository.ConveniosDao;
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
 * Created by RICHARD on 02/06/2014.
 */
@Repository(value = "conveniosDao")
public class ConveniosDaoImpl implements ConveniosDao {

    private static final Logger logger = LoggerFactory
            .getLogger(ConveniosDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Convenios> getByClientes(int idCliente) {
        try {
            Query query = em.createNamedQuery("Convenios.getByClientes");
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Convenios>();
        }
    }

    @Override
    public Convenios getByCliente(int idCliente) {
        try {
            Query query = em.createNamedQuery("Convenios.getByCliente");
            query.setParameter("idCliente", idCliente);
            Convenios convenios = (Convenios) query.getResultList().get(0);
            return convenios;
        }catch (NoResultException e){
            logger.error("Error al buscar Convenio by cliente ",e);
            return new Convenios();
        }catch (Exception e){
            logger.error("Error al buscar Convenio by cliente ",e);
            return new Convenios();
        }
    }

    @Transactional(readOnly = false)
    public Convenios insertConvenio(Convenios convenio) {
        try{
            em.persist(convenio);
            em.flush();
            return convenio;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new Convenios();
        }
    }

}
