package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

abstract public class CRUDDAo<T extends Model> {
    
    protected EntityManager em;
    
    abstract protected Class<T> getEntityClass();
    
    public CRUDDAo( EntityManager em ) {
        this.em = em;
    }
    
    public CRUDDAo() {
        this.em = JpaUtil.obtenirEntityManager();
        if( null == this.em ) {
            throw new RuntimeException("JpaUtil.creerEntityManager() must be call before the creation of a DAO");
        }
    }
    
    public T insert( T e ) {
        em.persist(e);
        return e;
    }
   
    public T update( T e ) {
        return (T) em.merge(e);
    }
    
    public T findById(long id) throws Exception {
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
        List<T> models = new ArrayList<>();
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
        em.remove(e);
    }
    
    public boolean contains( T e ) {
        
        if( null == e) {
            return false;
        }
        Query q = em.createQuery("Select m FROM " + getEntityClass().getSimpleName() + " m Where m = :object", getEntityClass());
        return q.setParameter("object", e).getResultList().size() > 0;
        
    }
    
    
}
