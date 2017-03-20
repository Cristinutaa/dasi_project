package com.gustatif.dasi_project.metier.service;

import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.gustatif.dasi_project.config.Config;
import com.gustatif.dasi_project.dao.LivreurDAO;
import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.modele.Commande;
import com.gustatif.dasi_project.metier.modele.Livraison;
import com.gustatif.dasi_project.metier.modele.Livreur;
import com.gustatif.dasi_project.metier.modele.LivreurDrone;
import com.gustatif.dasi_project.metier.modele.LivreurPersonne;
import com.gustatif.dasi_project.metier.modele.Restaurant;
import com.gustatif.dasi_project.util.FakeMailer;
import com.gustatif.dasi_project.util.GeoTest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


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
    
    public static boolean calculerCoordonnees(Livreur l) {
        
        LatLng coordonates = GeoTest.getLatLng(l.getAdresse_base());
        
        if( null == coordonates ) {
            return false;
        }
        
        l.setLocation(coordonates);
        return true;
    }
    
    public static Double getDuration( Livreur livreur, Restaurant restaurant, Client client ) {
        
        GeoTest geoTest = new GeoTest();
        
        if( livreur instanceof LivreurDrone ) {
            LivreurDrone lDrone = (LivreurDrone) livreur;
            
            return geoTest.getTripDurationOrDistance(TravelMode.UNKNOWN, false, livreur.getLocation(), client.getLocation(), restaurant.getPosition())/(lDrone.getVitesseMoyenne()/60.0);
            
        } else {
            LivreurPersonne lPersonne = (LivreurPersonne) livreur;
            return geoTest.getTripDurationByBicycleInMinute(lPersonne.getLocation(), client.getLocation(), restaurant.getPosition());
        }
        
    }
    
    public static Livreur findMeilleurLivreurPour( LivreurDAO livreurDAO, Livraison l ) {
        
        final Commande commande = l.getCommande();
        
        try {
            List<Livreur> livreurs = livreurDAO.findByChargeNecessaireEtLibre(commande.getPoids());
            
            if( livreurs.size() == 0 ) {
                return null;
            }
            
            Collections.sort(livreurs, new Comparator<Livreur>() {
                
                @Override
                public int compare(Livreur o1, Livreur o2) {
                    Double distanceO1 = getDuration( o1, commande.getRestaurant(), commande.getClient() );
                    Double distanceO2 = getDuration( o2, commande.getRestaurant(), commande.getClient() );
                    
                    if( distanceO1 < distanceO2 ) {
                        return -1;
                    } else if ( distanceO1 == distanceO2 ) {
                        return 0;
                    } else {
                        return 1;
                    }
                    
                }
            });
            
            return livreurs.get(0);
            
        } catch (Exception ex) {
            return null;
        }
        
    }
    
    public static boolean calculerCoordonnees(Client c) {
        
        LatLng coordonates = GeoTest.getLatLng(c.getAdresse());
        
        if( null == coordonates ) {
            return false;
        }
        
        c.setLatitudeLongitude( coordonates.lat, coordonates.lng );
        return true;
    }
    
}
