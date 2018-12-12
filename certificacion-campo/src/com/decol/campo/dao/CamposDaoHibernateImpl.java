/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decol.campo.dao;


import com.decol.campo.modelos.Campo;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author gabrielmainero
 */
public class CamposDaoHibernateImpl implements CamposDao{

    private final SessionFactory sessionFactory;

    public CamposDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public boolean existeNombre(String nombre) {
        boolean retorno = false;
        Session session = sessionFactory.openSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Campo> query = builder.createQuery(Campo.class);
        Root<Campo> root = query.from(Campo.class);
        query.select(root);
        query.where(builder.equal(root.get("nombre"), nombre));
        
        Campo campo = session.createQuery(query).uniqueResult();
        
        session.close();
        
        if(campo!=null)
            retorno = true;
        return retorno;
    }

    @Override
    public void guardar(Campo campo) {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(campo);
        session.getTransaction().commit();
        session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
