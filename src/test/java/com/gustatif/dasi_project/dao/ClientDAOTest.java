/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.config.Config;
import com.gustatif.dasi_project.metier.modele.Client;
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
 * @author rloic
 */
public class ClientDAOTest {
    
    public ClientDAOTest() {
        JpaUtil.creerEntityManager();
    }
    
    @BeforeClass
    public static void setUpClass() {
        JpaUtil.init( Config.TEST_PERSISTENCE );
        
        ClientDAO cDAO = new ClientDAO( JpaUtil.obtenirEntityManager() );
        JpaUtil.ouvrirTransaction();
        try {
            for( Client c : cDAO.findAll() ) {
                cDAO.remove(c);
            }
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
        }
    }
    
    @AfterClass
    public static void tearDownClass() {      
        JpaUtil.destroy();
    }
    
    @Test
    public void createRemoveTest() {
        
        ClientDAO cDAO = new ClientDAO( JpaUtil.obtenirEntityManager() );
        
        JpaUtil.ouvrirTransaction();
        
        Client c1 = new Client("nom_client", "prenom_client", "mail_client", "adresse_client");
        c1 = cDAO.insert(c1);
        
        try {
            assertTrue(cDAO.findAll().contains(c1));
            cDAO.remove(c1);
            assertFalse(cDAO.findAll().contains(c1));
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getLogger(ClientDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }
    
    @Test
    public void findByEmailTest() {
        
        ClientDAO cDAO = new ClientDAO( JpaUtil.obtenirEntityManager() );
        Client c1 = new Client("nom_client", "prenom_client", "mail_client", "adresse_client");
        c1.setLatitudeLongitude(0.0, 0.0);
        JpaUtil.ouvrirTransaction();
        c1 = cDAO.insert(c1);
        if(null == c1) {
            JpaUtil.annulerTransaction();
        } else {
            JpaUtil.validerTransaction();
        }
        
        assertNotNull( cDAO.findByEmail("mail_client") );
        assertNull( cDAO.findByEmail("fake_mail") );
        
        if( null != c1 ) {
            JpaUtil.ouvrirTransaction();
            cDAO.remove(c1);
            JpaUtil.validerTransaction();
        }
        
    }
    
    public void findByIdTest() {
        
        ClientDAO cDAO = new ClientDAO( JpaUtil.obtenirEntityManager() );
        
        Client c1 = new Client("nom_client", "prenom_client", "mail_client", "adresse_client");
        c1.setLatitudeLongitude(0.0, 0.0);
        
        JpaUtil.ouvrirTransaction();
        
        c1 = cDAO.insert(c1);
        
        JpaUtil.validerTransaction();
        
        try {
            assertNotNull(cDAO.findById( c1.getId() ));
            assertNull(cDAO.findById(-1l));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        JpaUtil.ouvrirTransaction();
        cDAO.remove(c1);
        JpaUtil.validerTransaction();
        
    }
    
}
