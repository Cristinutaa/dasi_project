package com.gustatif.dasi_project.vue;

import com.gustatif.dasi_project.config.Config;
import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.exception.InvalidActionException;
import com.gustatif.dasi_project.exception.InvalidReferenceException;
import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.modele.Commande;
import com.gustatif.dasi_project.metier.modele.Restaurant;
import com.gustatif.dasi_project.metier.service.ServiceMetier;
import com.gustatif.dasi_project.util.Saisie;

import java.util.Arrays;

public class MainClient {
    
    public static void main( String[] args ) {
        
        JpaUtil.init( Config.TEST_PERSISTENCE );
        JpaUtil.creerEntityManager();
        final ServiceMetier services = new ServiceMetier();

        Utility.initData();

        System.out.println("Liste des clients : ");
        Utility.display(services.findClients());
        Client client = null;
        String option;
        while( null == client) {
            option = Saisie.lireChaine("S'Enregistrer(e)\n S'authentifier(a)\n Choix : ", Arrays.asList("e", "a"));

            if(option.equals("e")) {
                client = enregistrement(services);
            } else {
                client = authentification(services);
            }
        }

        option = Saisie.lireChaine("Faire une commande(o/n)", Arrays.asList("o", "n"));

        if( "n".equals(option) ) {
            return;
        } else {

            Utility.display(services.getPictureUrlOf(services.findAllRestaurants()));
            Utility.display(services.findAllRestaurants());

            Restaurant restaurant = services.findRestaurantById(Saisie.lireInteger("Id du restaurant : ").longValue());
            while( null == restaurant ) {
                restaurant = services.findRestaurantById(Saisie.lireInteger("(Id invalide) > Id du restaurant : ").longValue());
            }

            Commande commande = null;
            try {
                commande = services.creerCommande(client, restaurant);
            } catch (InvalidReferenceException e) {
                System.err.println(e.getMessage());
                return;
            }

            Utility.display(commande.getRestaurant().getProduits());

            option = Saisie.lireChaine("Ajouter/Enlever produit/Finaliser (a/e/f) : ", Arrays.asList("a", "e", "f"));
            while( !"f".equals(option) ) {

                switch (option) {
                    case "a":
                        try {
                            services.ajouterProduit(commande, commande.getRestaurant().getProduits().get(Saisie.lireInteger("Index du produit ", 0, commande.getRestaurant().getProduits().size() - 1)));
                        } catch (InvalidReferenceException e) {
                            System.err.println(e.getMessage());
                        } catch (InvalidActionException e) {
                            System.err.println(e.getMessage());;
                        }
                        break;
                    case "e":
                        try {
                            services.enleverProduit(commande, commande.getRestaurant().getProduits().get(Saisie.lireInteger("Index du produit ", 0, commande.getRestaurant().getProduits().size() - 1)));
                        } catch (InvalidReferenceException e) {
                            System.err.println(e.getMessage());
                        } catch (InvalidActionException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                }

                System.out.println("Contenu de la commande : ");
                Utility.display(commande.getLignesCommandes());

                option = Saisie.lireChaine("Ajouter/Enlever produit/Finaliser (a/e/f) : ", Arrays.asList("a", "e", "f"));

            }

            try {
                services.validerCommande(commande);
            } catch (InvalidReferenceException e) {
                System.err.println(e.getMessage());
            } catch (InvalidActionException e) {
                System.err.println(e.getMessage());
            }

        }
        
        JpaUtil.destroy();
        
    }
    
    
    public static Client enregistrement(ServiceMetier services) {
        
        Client client = null;
        
        while( null == client) {
            String nom = Saisie.lireChaine("Nom : ");
            String prenom = Saisie.lireChaine("Prenom : ");
            String email = Saisie.lireChaine("Email : ");
            String adresse = Saisie.lireChaine("adresse");
        
            try {
                client = services.enregistrerClient(nom, prenom, email, adresse);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        
        }
    
        return client;
        
    }
    
    public static Client authentification(ServiceMetier services) {
        
        Client client = null;
        while( null == client ) {
            
            client = services.authentifierClient(Saisie.lireChaine("Adresse email : "));
            
        }
        
        return client;
        
    }


   
}
