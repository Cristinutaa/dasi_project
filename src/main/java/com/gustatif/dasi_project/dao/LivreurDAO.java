
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
    
    /**
     * Trouver le meilleure livreur pour une commande
     * @param l - la commande de client
     * @return Livreur - le meilleur livreur pour cette commande
     */
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
 
    @Override
    protected Class<Livreur> getEntityClass() {
        return Livreur.class;
    }
    
}
