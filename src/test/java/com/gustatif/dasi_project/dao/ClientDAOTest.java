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
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getLogger(ClientDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JpaUtil.validerTransaction();
        
    }
    
}
