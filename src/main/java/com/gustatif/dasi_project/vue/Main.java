package com.gustatif.dasi_project.vue;

import com.gustatif.dasi_project.dao.ClientDAO;
import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.service.ServiceMetier;
import java.util.List;

/**
 *
 * @author carhiliuc
 */
public class Main {
    
    public static ServiceMetier serviceMetier = new ServiceMetier();
    protected static ClientDAO clientDAO = new ClientDAO();
    
    public static void main( String[] args ) throws Exception {
      
        JpaUtil.init();
        JpaUtil.creerEntityManager();
        
        System.out.println( serviceMetier.findRestaurantById(1l) );
        
        JpaUtil.destroy();
        
    }
}
