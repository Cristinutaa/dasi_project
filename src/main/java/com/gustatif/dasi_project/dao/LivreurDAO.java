/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Livreur;
import com.gustatif.dasi_project.metier.modele.LivreurDrone;
import com.gustatif.dasi_project.metier.modele.LivreurPersonne;
import java.util.ArrayList;
import java.util.List;
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
