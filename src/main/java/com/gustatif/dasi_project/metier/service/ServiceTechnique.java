package com.gustatif.dasi_project.metier.service;

import com.gustatif.dasi_project.config.Config;
import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.modele.Commande;
import com.gustatif.dasi_project.metier.modele.Livraison;
import com.gustatif.dasi_project.metier.modele.LivreurPersonne;
import com.gustatif.dasi_project.util.FakeMailer;


public class ServiceTechnique {
    
    public static void envoyerMailConfirmationInscription( Client client ) {
        
        FakeMailer.sendMail(
                Config.ADMIN_MAIL, 
                client.getMail(), 
                "Bienvenue chez Gustat'IF", 
                "Bonjour "+ client.getNom() + ", \n" +
                "Nous vous confirmons votre inscription au service " +
                " GUSTAT’IF. Votre numéro de client est: " + client.getId());
        
    }
    
    public static void envoyerMailEchecInscription( Client client ) {
        FakeMailer.sendMail(
                Config.ADMIN_MAIL,
                client.getMail(), 
                "Bienvenue chez Gustat'IF",
                "Bonjour " + client.getNom() + ", \n" +
                "Votre inscription   au   service GUSTAT’IF" +
                " a malencontreusement échoué...   Merci de recommencer ultérieurement.");
    }
    
    public static void envoyerMailAssignationLivraison( Livraison l, LivreurPersonne lp ) {
     
        FakeMailer.sendMail(
                Config.ADMIN_MAIL,
                lp.getMail(), 
                "Livraison n° " + l.getId() + " à effectuer",
                "Bonjour " + lp.getPrenom() + " Merci d'effectuer cette livraison dès maintenant,"
                        + "tout en respectant le code de la route ;=) \n Le chef \n" + l.toFormattedString());
    }
    
}
