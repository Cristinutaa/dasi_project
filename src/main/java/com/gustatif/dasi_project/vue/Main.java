/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.vue;

import com.gustatif.dasi_project.dao.ClientDAO;
import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.dao.LivraisonDAO;
import com.gustatif.dasi_project.dao.ProduitDAO;
import com.gustatif.dasi_project.dao.RestaurantDAO;
import com.gustatif.dasi_project.metier.modele.Livraison;
import com.gustatif.dasi_project.metier.modele.Produit;
import com.gustatif.dasi_project.metier.modele.Restaurant;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author carhiliuc
 */
public class Main {
    public static void main( String[] args ) {
      
        JpaUtil.init();
        JpaUtil.creerEntityManager();
       
        
        JpaUtil.destroy();
        
    }
}
