package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Model;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

abstract public class CRUDDAo<T extends Model> {
    
    abstract protected Class<T> getEntityClass();
    
    public T insert( T e ) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(e);
        return e;
    }
   
    public T update( T e ) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return (T) em.merge(e);
    }
    
    public T findById(long id) throws Exception {
        EntityManager em = JpaUtil.obtenirEntityManager();
        T t = null;
        try {
            t = (T) em.find(getEntityClass(), id);
        }
        catch(Exception e) {
            throw e;
        }
        return t;
    }
    
    public List<T> findAll() throws Exception {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<T> models = null;
        try {
            Query q = em.createQuery("SELECT m FROM " + getEntityClass().getSimpleName() + " m");
            models = (List<T>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return models;
    }
    
    public void remove( T e ) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.remove(e);
    } 
    
    
}
