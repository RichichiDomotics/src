package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.TraspasosCamara;
import com.cbj.almacen.repository.TraspasosCamaraDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by RICHARD on 09/09/2014.
 */
@Repository(value = "traspasosCamaraDao")
public class TraspasosCamaraDaoImpl implements TraspasosCamaraDao {

    private static final Logger logger = LoggerFactory
            .getLogger(ServiciosDaoImpl.class);

    private EntityManager em = null;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public TraspasosCamara insertTraspasoCamara(TraspasosCamara traspasosCamara) {
        try{
            em.persist(traspasosCamara);
            em.flush();
            return traspasosCamara;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new TraspasosCamara();
        }
    }
}
