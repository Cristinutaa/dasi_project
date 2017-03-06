package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Model;
import javax.persistence.EntityManager;

public class CRUDDAo<T extends Model> {
    
    public void insert( T e ) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(e);
    }
   
    public T update( T e ) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return (T) em.merge(e);
    }
    
}
