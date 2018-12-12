/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decol.campo.dao;


import com.decol.campo.modelos.TipoSuelo;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Gabriel Mainero
 */
public class TiposSueloDaoHibernateImpl implements TiposSueloDao {
    
    private final SessionFactory sessionFactory;

    public TiposSueloDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<TipoSuelo> obtenerTodos() {
        //Abrimos sesion
        Session session = sessionFactory.openSession();
//        List<TipoSuelo> retorno = session.createQuery("from TipoSuelo").list();
        
        CriteriaQuery<TipoSuelo> query = session.getCriteriaBuilder().createQuery(TipoSuelo.class);
        query.select(query.from(TipoSuelo.class));
        List<TipoSuelo> retorno = session.createQuery(query).list();
        
        //Cerramos la session
        session.close();
        
        return retorno;
    }
}
