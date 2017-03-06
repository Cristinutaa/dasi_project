package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.modele.Livraison;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class LivraisonDAO extends CRUDDAo<Livraison> {

    @Override
    protected Class<Livraison> getEntityClass() {
        return Livraison.class;
    }

    public List<Livraison> findByClient( Client client ) {
        return new ArrayList<>();
    }
    
    public Livraison findLastOfClient( Client client ) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Livraison l = null;
        try {
            Query q = em.createQuery("Select l From Livraison l where livraison.client = :client OrderBy livraison.dateDebut DESC");
            l = (Livraison) q.getSingleResult();
        } catch( Exception e ) {}
        return l;
    }
    
    public List<Livraison> findNonLivrees() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Livraison> livraisons = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT l From Livraison l where livraison.livree = FALSE");
            livraisons = (List<Livraison>) q.getResultList();
        }
        catch(Exception e) {}
        return livraisons;
    }
    
}
