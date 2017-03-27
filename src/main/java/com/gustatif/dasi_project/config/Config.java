package com.gustatif.dasi_project.config;

/**
 * Classe de configuration de l'application
 * @author rloic
 */
public class Config {
    
    /**
     * Nom de domaine de l'application
     */
    public static String DOMAIN_NAME = "gustatif.com";
    
    /**
     * Adresse mail de l'administrateur
     */
    public static String ADMIN_MAIL = "gustatif" + "@" + DOMAIN_NAME;
    
    /**
     * Unite de persistence de developpement
     */
    public static String DEVELOPMENT_PERSISTENCE = "com.gustatif_dasi_persistance_unit_dev";
    
    /**
     * Unite de persistence de test
     */
    public static String TEST_PERSISTENCE = "com.gustatif_dasi_persistance_unit_test";
    
    /**
     * Url de l'image par défaut pour les restaurants
     */
    public static String DEFAULT_RESTAURANT_PICTURE_URL = "";
    
}
