package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Livraison;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Le Data Access object chargé de manipuler les Livraison
 * @author Loic
 */
public class LivraisonDAO extends CRUDDAo<Livraison> {

    /**
     * Constructeur par défaut
     */
    public LivraisonDAO() {
        super();
    }
    
    /**
     * Constructeur avec entity manager. Utilise l'entity manager pour effectuer
     * la persistance
     * @param em Entity Manager
     */
    public LivraisonDAO(EntityManager em) {
        super(em);
    }

    /**
     * Renvoie le nom de la classe gérée par LivraisonDAO
     * @return Class<Livraison> la classe gérée par le DAO
     */
    @Override
    protected Class<Livraison> getEntityClass() {
        return Livraison.class;
    }

    /**
     * Renvoie les livraisons attribuées à un drone
     * @return List<Livraison>
     */
    public List<Livraison> findLivraisonsParDrone() {
        
        List<Livraison> livraisons = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT l From Livraison l where l.livreur in (Select li From LivreurDrone li)");
            livraisons = (List<Livraison>) q.getResultList();
        }
        catch(Exception e) {}
        return livraisons;
        
    }
    
}
