package com.gustatif.dasi_project.vue;

import com.gustatif.dasi_project.config.Config;
import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.exception.EmailAlreadyUsedException;
import com.gustatif.dasi_project.exception.EmptyAttributeException;
import com.gustatif.dasi_project.exception.InvalidAddressException;
import com.gustatif.dasi_project.metier.service.ServiceMetier;
import com.gustatif.dasi_project.util.UrlPictureSearcher;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carhiliuc
 */
public class Main {
    
    public static void main( String[] args ) {
        
        JpaUtil.init( Config.TEST_PERSISTENCE );
        JpaUtil.creerEntityManager();
        final ServiceMetier services = new ServiceMetier();
        
        System.out.println(UrlPictureSearcher.getUrlPictureForRestaurant("Le montesqieu"));
        
        JpaUtil.destroy();
        
    }
    
}
