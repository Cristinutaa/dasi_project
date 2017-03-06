/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.vue;

import com.gustatif.dasi_project.dao.ClientDAO;
import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.dao.ProduitDAO;
import com.gustatif.dasi_project.dao.RestaurantDAO;
import com.gustatif.dasi_project.metier.modele.Produit;
import com.gustatif.dasi_project.metier.modele.Restaurant;
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
        JpaUtil.ouvrirTransaction();
        ClientDAO cDAO = new ClientDAO();
        cDAO.save(new Client("Jean", "Louis", "jean@louis.fr", "Place de la république, 69002 Lyon"));
        JpaUtil.validerTransaction();
        
        try {
            System.out.println(cDAO.findAll());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JpaUtil.ouvrirTransaction();
        ProduitDAO pDAO = new ProduitDAO();
        pDAO.save(new Produit("Carrot", "Bon", 0.5, 2.0));
        JpaUtil.validerTransaction();
        
        try {
            System.out.println(pDAO.findAll());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JpaUtil.ouvrirTransaction();
        RestaurantDAO rDAO = new RestaurantDAO();
        rDAO.save(new Restaurant("Lapin", "Avec de la nouritture", "Ici"));
        JpaUtil.validerTransaction();
        
        try {
            System.out.println(rDAO.findAll());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JpaUtil.destroy();
        
    }
}
