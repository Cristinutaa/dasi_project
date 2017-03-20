package com.gustatif.dasi_project.vue;

import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.service.ServiceMetier;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    
    public static void main( String[] args ) {
        
        JpaUtil.init();
        JpaUtil.creerEntityManager();
        final ServiceMetier services = new ServiceMetier();
       
        menu();
        managerStory(services);
        
        JpaUtil.destroy();
        
    }
    
    public static void menu() {
        
        System.out.println("**************************************");
        System.out.println("Choisir son histoire : ");
        System.out.println("1. Enregistrement");
        System.out.println("2. Creation d'une commande");
        System.out.println("3. Affichage du tableau de bord");
        System.out.println("4. Validation d'une commande (manager)");
        System.out.println("5. Validation d'une commande (livreur)");
        System.out.println("**************************************");
        
        
        
    }
    
    public static void clientCommandStory( ServiceMetier services ) {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        try {
            Client client = services.authentifierClient(br.readLine());
            
            if( null == client ) {
                System.out.println("Identifiant incorrect");
                return;
            }
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
    public static void clientRegisterStory( ServiceMetier services ) {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            System.out.print("Nom : ");
            String nom = br.readLine();
            System.out.print("Prenom : ");
            String prenom = br.readLine();
            System.out.print("Email : ");
            String email = br.readLine();
            System.out.print("Adresse : ");
            String adresse = br.readLine();
            
            services.enregistrerClient(nom, prenom, email, adresse);
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    public static void managerStory( ServiceMetier services ) {
        
        System.out.println(services.findAllRestaurants());
        
    }
    
}
