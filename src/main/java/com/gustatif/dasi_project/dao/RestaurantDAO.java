package com.gustatif.dasi_project.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.gustatif.dasi_project.metier.modele.Restaurant;

public class RestaurantDAO extends CRUDDAo<Restaurant>{
    
    public RestaurantDAO() {
        super();
    }
    
    public RestaurantDAO( EntityManager em ) {
        super(em);
    }
    
    public List<Restaurant> findByName(String nom) throws Exception {
        List<Restaurant> restaurants = null;
        try {
            Query q = em.createQuery("SELECT r FROM Restaurant r WHERE r.denomination = :nom");
            restaurants = (List<Restaurant>) q.setParameter("nom", nom + "%").getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return restaurants;
    }

    @Override
    protected Class<Restaurant> getEntityClass() {
        return Restaurant.class;
    }
}
