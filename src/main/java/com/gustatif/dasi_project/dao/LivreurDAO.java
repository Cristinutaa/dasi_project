/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public Livreur findMeilleurLivreurPour( Livraison l ) {
        
        final Commande commande = l.getCommande();
        
        try {
            List<Livreur> livreurs = findByChargeNecessaireEtLibre(commande.getPoids());
            
            if( livreurs.size() == 0 ) {
                return null;
            }
            
            Collections.sort(livreurs, new Comparator<Livreur>() {
                
                @Override
                public int compare(Livreur o1, Livreur o2) {
                    Double distanceO1 = o1.getDistance(o1.getLocation(), commande.getRestaurant().getPosition(), commande.getClient().getPoisition());
                    Double distanceO2 = o2.getDistance(o2.getLocation(), commande.getRestaurant().getPosition(), commande.getClient().getPoisition());
                    
                    if( distanceO1 < distanceO2 ) {
                        return -1;
                    } else if ( distanceO1 == distanceO2 ) {
                        return 0;
                    } else {
                        return 1;
                    }
                    
                }
            });
            
            return null;
            
        } catch (Exception ex) {
            return null;
        }
        
    }
         
    
     public List<LivreurDrone> findByVitesse(double vitesse) throws Exception {
        List<LivreurDrone> livreurs = null;
        try {
            Query q = em.createQuery("SELECT l FROM LivreurDrone l WHERE l.vitesseMoyenne = :vitesse");
            q.setParameter("vitesse", vitesse);
            livreurs = (List<LivreurDrone>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return livreurs;
    }
     
    public List<Livreur> findLibre() {
        // TODO
        return new ArrayList<>();
    }
    
    public List<Livreur> findNonLibre() {
        // TODO
        return new ArrayList<>();
    }
    
    @Override
    protected Class<Livreur> getEntityClass() {
        return Livreur.class;
    }
    
}
