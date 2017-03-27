package com.gustatif.dasi_project.vue;

import com.gustatif.dasi_project.config.Config;
import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.exception.InvalidActionException;
import com.gustatif.dasi_project.exception.InvalidReferenceException;
import com.gustatif.dasi_project.metier.modele.Livraison;
import com.gustatif.dasi_project.metier.modele.LivreurDrone;
import com.gustatif.dasi_project.metier.service.ServiceMetier;
import com.gustatif.dasi_project.util.Saisie;

import java.util.Arrays;

public class MainModerateur {

    public static void main(String[] args) {


        JpaUtil.init( Config.TEST_PERSISTENCE );
        JpaUtil.creerEntityManager();
        final ServiceMetier services = new ServiceMetier();

        System.out.println("*************************************************");
        System.out.println("");
        System.out.println("Restaurants : ");
        Utility.display(services.findAllRestaurants());
        System.out.println("Clients inactifs : ");
        Utility.display(services.findClientsInactifs());
        System.out.println("Clients en attente de livraison : ");
        Utility.display(services.findClientsEnAttenteDeLivraison());
        System.out.println("Livreurs libres : ");
        Utility.display(services.findLivreursLibres());
        System.out.println("Livreurs occupés : ");
        Utility.display(services.findLivreursOccupes());
        System.out.println("");
        System.out.println("*************************************************");

        String option = Saisie.lireChaine("Voulez-vous confirmer une livraison de drone ? (o/n)", Arrays.asList("o", "n"));

        if( "n".equals(option) ) {
            return;
        }

        Utility.display(services.findLivraisonsParDrone());

        Livraison livraison = services.findLivraisonsParDrone().get( Saisie.lireInteger("Index de la livraison a confirmer", 0, services.findLivraisonsParDrone().size()-1));

        try {
            services.validerLivraison(livraison);
        } catch (InvalidReferenceException e) {
            System.err.println(e.getMessage());
        } catch (InvalidActionException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("*************************************************");
        System.out.println("");
        System.out.println("Restaurants : ");
        Utility.display(services.findAllRestaurants());
        System.out.println("Clients inactifs : ");
        Utility.display(services.findClientsInactifs());
        System.out.println("Clients en attente de livraison : ");
        Utility.display(services.findClientsEnAttenteDeLivraison());
        System.out.println("Livreurs libres : ");
        Utility.display(services.findLivreursLibres());
        System.out.println("Livreurs occupés : ");
        Utility.display(services.findLivreursOccupes());
        System.out.println("");
        System.out.println("*************************************************");

        JpaUtil.destroy();

    }

}
