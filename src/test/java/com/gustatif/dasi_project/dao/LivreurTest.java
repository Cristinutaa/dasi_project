/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.modele.Commande;
import com.gustatif.dasi_project.metier.modele.Livraison;
import com.gustatif.dasi_project.metier.modele.LivreurDrone;
import com.gustatif.dasi_project.metier.modele.Restaurant;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author carhiliuc
 */
public class LivreurTest {
    
    public LivreurTest() {
         JpaUtil.creerEntityManager();
    }
    
    @BeforeClass
    public static void setUpClass() {
        JpaUtil.init();
    }
    
    @AfterClass
    public static void tearDownClass() {
        JpaUtil.destroy();
    }
    
  
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void createRemoveTest(){
        LivreurDAO l = new LivreurDAO();
        
        JpaUtil.ouvrirTransaction();
        LivreurDrone livreur = new LivreurDrone(2.5, 25, "rue Blablabla");
        l.insert(livreur);
        
     
        try {
            assertTrue(l.findAll().contains(livreur));
            l.remove(livreur);
            assertFalse(l.findAll().contains(livreur));
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getLogger(LivreurTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
  
    @Test
    public void chercheLivreurTest(){
        LivreurDAO l = new LivreurDAO();
        
        JpaUtil.ouvrirTransaction();
        LivreurDrone livreur = new LivreurDrone(2.5, 25, "rue Blablabla");
        l.insert(livreur);
         LivreurDrone livreur1 = new LivreurDrone(2.5, 50, "rue Blablabla");
        l.insert(livreur1);
     
        try {
            assertTrue(l.findByChargeNecessaireEtLibre(30).contains(livreur1));
            assertFalse(l.findByChargeNecessaireEtLibre(30).contains(livreur));
            l.remove(livreur);
            l.remove(livreur1);
            assertFalse(l.findAll().contains(livreur));
            assertFalse(l.findAll().contains(livreur1));
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getLogger(LivreurTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Test
    public void chercheLivreurTestNonLibre(){
        LivreurDAO l = new LivreurDAO();
        
        JpaUtil.ouvrirTransaction();
        LivreurDrone livreur = new LivreurDrone(2.5, 25, "rue Blablabla");
        l.insert(livreur);
        LivreurDrone livreur1 = new LivreurDrone(2.5, 50, "rue Blablabla");
        livreur1.setLibre(false);
        l.insert(livreur1);
     
        try {
            assertFalse(l.findByChargeNecessaireEtLibre(30).contains(livreur1));
            assertFalse(l.findByChargeNecessaireEtLibre(30).contains(livreur));
            l.remove(livreur);
            l.remove(livreur1);
            assertFalse(l.findAll().contains(livreur));
            assertFalse(l.findAll().contains(livreur1));
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getLogger(LivreurTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Test
    public void findMeilleurLivreurTest(){
        LivreurDAO l = new LivreurDAO();
        
        LivreurDrone livreur = new LivreurDrone(2.5, 25, "3 avenue Albert Einstein, Villeurbanne, France ");
        l.insert(livreur);
        LivreurDrone livreur1 = new LivreurDrone(2.5, 50, "rue des rosiers, Paris, France ");
        l.insert(livreur1);
        
        Livraison livrer = new Livraison(new Commande(new Client("Vasile", "Andornevici", "alala@yahoo.com", "Rue de la Republique, Lyon, France"), new Restaurant("Pizza", "Pizza Gratuite", "Cours Emille Zola, Villeurbanne France")));
        try {
            assertTrue(l.findMeilleurLivreurPour(livrer) == livreur);
            l.remove(livreur);
            l.remove(livreur1);  
            assertFalse(l.findAll().contains(livreur));
            assertFalse(l.findAll().contains(livreur1));
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getLogger(LivreurTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
}
