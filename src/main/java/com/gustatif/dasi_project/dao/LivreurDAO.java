
package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Commande;
import com.gustatif.dasi_project.metier.modele.Livraison;
import com.gustatif.dasi_project.metier.modele.Livreur;
import com.gustatif.dasi_project.metier.modele.LivreurDrone;
import com.gustatif.dasi_project.metier.modele.LivreurPersonne;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author carhiliuc
 */
public class LivreurDAO extends CRUDDAo<Livreur>{

    public LivreurDAO() {
        super();
    }
    
    public LivreurDAO(EntityManager em) {
        super(em);
    }
        
    /**
     * Chercher les livreurs qui peuvent deplacer le poids passé en parametre et qui sont libre au moment 
     * @param poids - le poids des produits de la livraison
     * @return List<Livreur> - la liste des livreurs 
     * @throws Exception - Exception de connexion
     */ 
    public List<Livreur> findByChargeNecessaireEtLibre(double poids) throws Exception {
        List<Livreur> livreurs = null;
        try {
            Query q = em.createQuery("SELECT l FROM Livreur l WHERE l.chargeMaximale >= :poids AND l.libre = true");
            q.setParameter("poids", poids);
            livreurs = (List<Livreur>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return livreurs;
    }      
    
    public List<Livreur> findLibres() {
        List<Livreur> livreurs = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT l FROM Livreur l WHERE l.libre = :etat");
            q.setParameter("etat", true);
            livreurs = (List<Livreur>) q.getResultList();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
        return livreurs;
    }
    
    public List<Livreur> findNonLibres() {
        List<Livreur> livreurs = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT l FROM Livreur l WHERE l.libre = :etat");
            q.setParameter("etat", false);
            livreurs = (List<Livreur>) q.getResultList();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
        return livreurs;
    }
 
    @Override
    protected Class<Livreur> getEntityClass() {
        return Livreur.class;
    }
    
}
