package com.gustatif.dasi_project.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.gustatif.dasi_project.metier.modele.Restaurant;
import java.util.ArrayList;

/**
 * Le Data Access object chargé de manipuler les Restaurant
 * @author Loic
 */
public class RestaurantDAO extends CRUDDAo<Restaurant>{
    
    /**
     * Constructeur par défaut
     */
    public RestaurantDAO() {
        super();
    }
    
    /**
     * Constructeur avec entity manager. Utilise l'entity manager pour effectuer
     * la persistance
     * @param em Entity Manager
     */
    public RestaurantDAO( EntityManager em ) {
        super(em);
    }
    
    /**
     * Renvoie la liste des restaurants dont le nom commence par nom. Si
     * aucun restaurant ne correspond, renvoie une liste vide
     * @param nom Le nom du restaurant recherché
     * @return List<Restaurant>
     */
    public List<Restaurant> findByName(String nom) {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT r FROM Restaurant r WHERE r.denomination = :nom");
            restaurants = (List<Restaurant>) q.setParameter("nom", nom + "%").getResultList();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
        return restaurants;
    }

    /**
     * Renvoie le nom de la classe gérée par RestaurantDAO
     * @return Class<Restaurant> la classe gérée par le DAO
     */
    @Override
    protected Class<Restaurant> getEntityClass() {
        return Restaurant.class;
    }
}
