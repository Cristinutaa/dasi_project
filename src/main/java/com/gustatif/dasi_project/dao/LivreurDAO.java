/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Livreur;
import com.gustatif.dasi_project.metier.modele.LivreurDrone;
import com.gustatif.dasi_project.metier.modele.LivreurPersonne;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author carhiliuc
 */
public class LivreurDAO extends CRUDDAo<Livreur>{
    
    public List<Livreur> findByChargeMaximale(double poids) throws Exception {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Livreur> livreurs = null;
        try {
            Query q = em.createQuery("SELECT l FROM Livreur l WHERE charge_maximale = " + poids);
            livreurs = (List<Livreur>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return livreurs;
    }
    
     public List<Livreur> findByChargeNecessaire(double poids) throws Exception {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Livreur> livreurs = null;
        try {
            Query q = em.createQuery("SELECT l FROM Livreur l WHERE charge_maximale >= " + poids);
            livreurs = (List<Livreur>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return livreurs;
    }
     
    public List<Livreur> findByChargeNecessaireEtLibre(double poids) throws Exception {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Livreur> livreurs = null;
        try {
            Query q = em.createQuery("SELECT l FROM Livreur l WHERE charge_maximale >= " + poids + " AND libre = true");
            livreurs = (List<Livreur>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return livreurs;
    }
        
     public List<LivreurPersonne> findByNom(String name) throws Exception {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<LivreurPersonne> livreurs = null;
        try {
            Query q = em.createQuery("SELECT l FROM LivreurPersonne l WHERE nom = " + name);
            livreurs = (List<LivreurPersonne>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return livreurs;
    }
     
    public List<LivreurPersonne> findByPrenom(String name) throws Exception {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<LivreurPersonne> livreurs = null;
        try {
            Query q = em.createQuery("SELECT l FROM LivreurPersonne l WHERE prenom = " + name);
            livreurs = (List<LivreurPersonne>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return livreurs;
    }
    
    public List<LivreurPersonne> findByNomPrenom(String nom, String prenom) throws Exception {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<LivreurPersonne> livreurs = null;
        try {
            Query q = em.createQuery("SELECT l FROM LivreurPersonne l WHERE prenom = " + prenom + " and nom = " + nom);
            livreurs = (List<LivreurPersonne>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return livreurs;
    }
    
     public List<LivreurDrone> findByVitesse(double vitesse) throws Exception {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<LivreurDrone> livreurs = null;
        try {
            Query q = em.createQuery("SELECT l FROM LivreurDrone l WHERE vitesse_moyenne = " + vitesse);
            livreurs = (List<LivreurDrone>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return livreurs;
    }
    
    
    
}
