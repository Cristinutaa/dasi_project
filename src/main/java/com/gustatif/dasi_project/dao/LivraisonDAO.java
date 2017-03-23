package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.modele.Livraison;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class LivraisonDAO extends CRUDDAo<Livraison> {

    public LivraisonDAO() {
        super();
    }
    
    public LivraisonDAO(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Livraison> getEntityClass() {
        return Livraison.class;
    }

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
