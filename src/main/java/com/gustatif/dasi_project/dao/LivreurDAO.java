
package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Livreur;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Le Data Access object chargé de manipuler les Livreur
 * @author carhiliuc
 */
public class LivreurDAO extends CRUDDAo<Livreur>{

    /**
     * Constructeur par défaut
     */
    public LivreurDAO() {
        super();
    }
    
    /**
     * Constructeur avec entity manager. Utilise l'entity manager pour effectuer
     * la persistance
     * @param em Entity Manager
     */
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
    
    /**
     * Renvoie les livreurs libres
     * @return List<Livreur>
     */
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
    
    /**
     * Renvoie les livreurs qui sont en train d'effectuer une livraison
     * @return List<Livreur>
     */
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
 
    /**
     * Renvoie le nom de la classe gérée par LivreurDAO
     * @return Class<Livreur> la classe gérée par le DAO
     */
    @Override
    protected Class<Livreur> getEntityClass() {
        return Livreur.class;
    }
    
}
