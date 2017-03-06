/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Client;
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
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEntityClass method, of class ClientDAO.
     */
    @Test
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        ClientDAO instance = new ClientDAO();
        Class<Client> expResult = null;
        Class<Client> result = instance.getEntityClass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
