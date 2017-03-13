package com.gustatif.dasi_project.metier.service;

import com.gustatif.dasi_project.config.Config;
import com.gustatif.dasi_project.dao.ClientDAO;
import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.dao.LivraisonDAO;
import com.gustatif.dasi_project.dao.LivreurDAO;
import com.gustatif.dasi_project.dao.ProduitDAO;
import com.gustatif.dasi_project.dao.RestaurantDAO;
import com.gustatif.dasi_project.exception.EmailAlreadyUsedException;
import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.modele.Livraison;
import com.gustatif.dasi_project.metier.modele.Restaurant;
import com.gustatif.dasi_project.util.FakeMailer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.NoResultException;

public class ServiceMetier {
    
    private ClientDAO clientDAO = new ClientDAO();
    private LivraisonDAO livraisonDAO = new LivraisonDAO();
    private LivreurDAO livreurDAO = new LivreurDAO();
    private ProduitDAO produitDAO = new ProduitDAO();
    private RestaurantDAO restaurantDAO = new RestaurantDAO();
    
    public ServiceMetier() {}
    
    /**
     * Creer un client dans la base de données. Les informations du clients doivent
     * être remplies et correcte.
     * Si l'ajout s'est correctement effectué, renvoie le client crée, et
     * envoie le mail de validation, sinon renvoie null et envoie le mail d'erreur
     * @param c Le client a créer
     * @throws EmailAlreadyUsedException Si l'email est déjà attribué à un autre client
     * @return Client/Null
     */
    public Client creerClient( Client c ) throws EmailAlreadyUsedException {
        Client clientCree = null;
        JpaUtil.ouvrirTransaction();
        try {
            
            clientCree = clientDAO.insert(c);
            
            if(clientCree == null) {
                JpaUtil.annulerTransaction();
                throw new EmailAlreadyUsedException();
            }
            
            JpaUtil.validerTransaction();
            
            FakeMailer.sendMail(
                Config.ADMIN_MAIL, 
                c.getMail(), 
                "Bienvenue chez Gustat'IF", 
                "Bonjour "+ c.getMail() + ", \n" +
                "Nous vous confirmons votre inscription au service " +
                " GUSTAT’IF. Votre numéro de client est: " + c.getId());
            
        } catch(Exception e) {
            
            JpaUtil.annulerTransaction();
            
            FakeMailer.sendMail(
                Config.ADMIN_MAIL,
                c.getMail(), 
                "Bienvenue chez Gustat'IF",
                "Bonjour " + c.getNom() + ", \n" +
                "Votre inscription   au   service GUSTAT’IF" +
                " a malencontreusement échoué...   Merci de recommencer ultérieurement.");
            
        }
        
        return clientCree;
        
    }
    
    /**
     * Recherche un utilisateur possédant le mail "mail". Si aucun client ne 
     * possède le mail "mail" renvoie null
     * @param mail Le mail à rechercher
     * @return Client/Null
     */
    public Client findClientByMail( String mail ) {
        Client client = null;
        try {
            client = clientDAO.findByEmail(mail);
        } catch( Exception e ) {
            System.err.println(e.getMessage());
        }
        return client;
    }
    
    /**
     * Renvoie la liste des clients de l'application. En cas d'erreur, renvoie
     * une liste vide
     * @return List<Client>
     */
    public List<Client> findClients() {
        
        try {
            return clientDAO.findAll();
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<>();
        
    }
    
    public List<Restaurant> findRestaurants() {
        
        try {
            return restaurantDAO.findAll();
        } catch( Exception e) {
            System.err.println(e.getMessage());   
        }
        return new ArrayList<>();
        
    }
    
    public Restaurant findRestaurantById( Long id ) {
        
        try {
            return restaurantDAO.findById( id );
        } catch( NoResultException noResult ) {
            return null;
        } catch( Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public Livraison passerCommande( Livraison l ) {
        
        
        
        return null;
        
    }
    
    public Livraison validerLivraison( Livraison l ) {
        
        try {
            
            l.setDateFin(Calendar.getInstance().getTime());
            return livraisonDAO.update(l);
            
        } catch( Exception e ) {
            System.err.println(e.getMessage());
        }
        
        return null;
        
    }
 
}
