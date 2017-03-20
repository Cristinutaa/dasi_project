package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.config.Config;
import com.gustatif.dasi_project.util.Validator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test pour la classe Validator
 * @author Loic
 */
public class ValidatorTest {
    
    public ValidatorTest() {
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
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test la méthode CheckNotNullAndNotEmpty(String)
     */
    @Test
    public void emptyAttributeTest() {
        
        assertFalse("Null must return false", Validator.CheckNotNullAndNotEmpty((String)null));
        assertFalse("'' must return false", Validator.CheckNotNullAndNotEmpty(""));
        assertTrue("j must return true", Validator.CheckNotNullAndNotEmpty("j"));
        
    }
    
    /**
     * Test de la méthode isMail(String)
     */
    @Test
    public void emailValidatorTest() {
        
        assertFalse("null must return false", Validator.isMail(null));
        assertFalse("'' must return false", Validator.isMail(""));
        assertTrue("test@test.com must return true", Validator.isMail("test@test.com"));
        assertFalse("test@test must return false", Validator.isMail("test@test"));
        assertFalse("@test.com must return false", Validator.isMail("@test.com"));
        assertFalse("test.com must return false", Validator.isMail("test.com"));
        assertTrue("test@test.com.fr must return true", Validator.isMail("test@test.com.fr"));
        assertFalse("test @test.com must return false", Validator.isMail("test @test.com"));
        assertFalse("testç@test.com must return false", Validator.isMail("testç@test.com"));
        assertFalse("test@test. must return false", Validator.isMail("test@test."));
        
    }
    
}
