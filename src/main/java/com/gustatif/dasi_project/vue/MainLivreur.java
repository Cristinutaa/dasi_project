package com.gustatif.dasi_project.vue;

import com.gustatif.dasi_project.config.Config;
import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.exception.InvalidActionException;
import com.gustatif.dasi_project.exception.InvalidReferenceException;
import com.gustatif.dasi_project.metier.modele.Livreur;
import com.gustatif.dasi_project.metier.service.ServiceMetier;
import com.gustatif.dasi_project.util.Saisie;

import java.util.Arrays;

public class MainLivreur {

    public static void main(String[] args) {

        JpaUtil.init( Config.TEST_PERSISTENCE );
        JpaUtil.creerEntityManager();
        final ServiceMetier services = new ServiceMetier();

        System.out.println("Livreurs en cours de livraisons : ");
        Utility.display( services.findLivreursOccupes() );

        Integer indexLivreur = Saisie.lireInteger("Choisir le livreur qui doit valider sa commande ", 0, services.findLivreursOccupes().size()-1);

        Livreur livreur = services.findLivreursOccupes().get(indexLivreur);

        String option = Saisie.lireChaine("Valider avec la date actuelle ou manuelle (a/m) : ", Arrays.asList("a", "m"));

        livreur.getLivraisons();

        switch (option) {
            case "a":
                try {
                    services.validerLivraison(livreur.getLivraisons().get( livreur.getLivraisons().size()-1 ));
                } catch (InvalidReferenceException e) {
                    System.err.println(e.getMessage());
                } catch (InvalidActionException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "m":
                try {
                    services.validerLivraison(livreur.getLivraisons().get( livreur.getLivraisons().size()-1 ), Saisie.lireDate("Saisir la date de livraison : "));
                } catch (InvalidReferenceException e) {
                    System.err.println(e.getMessage());
                } catch (InvalidActionException e) {
                    System.err.println(e.getMessage());
                }
                break;
        }

        System.out.println("Livreurs en cours de livraisons : ");
        Utility.display( services.findLivreursOccupes() );

        JpaUtil.destroy();

    }

}
