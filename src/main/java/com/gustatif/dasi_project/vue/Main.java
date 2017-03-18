package com.gustatif.dasi_project.vue;

import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.metier.service.ServiceMetier;

/**
 *
 * @author carhiliuc
 */
public class Main {
    
    public static void main( String[] args ) {
        
        JpaUtil.init();
        JpaUtil.creerEntityManager();
        final ServiceMetier services = new ServiceMetier();
                
        JpaUtil.destroy();
        
    }
}
