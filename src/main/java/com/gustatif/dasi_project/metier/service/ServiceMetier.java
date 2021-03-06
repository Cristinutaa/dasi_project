package com.gustatif.dasi_project.metier.service;

import com.gustatif.dasi_project.dao.ClientDAO;
import com.gustatif.dasi_project.dao.CommandeDAO;
import com.gustatif.dasi_project.dao.JpaUtil;
import com.gustatif.dasi_project.dao.LivraisonDAO;
import com.gustatif.dasi_project.dao.LivreurDAO;
import com.gustatif.dasi_project.dao.ProduitDAO;
import com.gustatif.dasi_project.dao.RestaurantDAO;
import com.gustatif.dasi_project.exception.EmailAlreadyUsedException;
import com.gustatif.dasi_project.exception.EmptyAttributeException;
import com.gustatif.dasi_project.exception.InvalidActionException;
import com.gustatif.dasi_project.exception.InvalidAddressException;
import com.gustatif.dasi_project.exception.InvalidEmailFormatException;
import com.gustatif.dasi_project.exception.InvalidReferenceException;
import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.modele.Commande;
import com.gustatif.dasi_project.metier.modele.LigneCommande;
import com.gustatif.dasi_project.metier.modele.Livraison;
import com.gustatif.dasi_project.metier.modele.Livreur;
import com.gustatif.dasi_project.metier.modele.LivreurPersonne;
import com.gustatif.dasi_project.metier.modele.Produit;
import com.gustatif.dasi_project.metier.modele.Restaurant;
import com.gustatif.dasi_project.util.Validator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

public class ServiceMetier {
    
    private ClientDAO clientDAO = new ClientDAO();
    private LivraisonDAO livraisonDAO = new LivraisonDAO();
    private LivreurDAO livreurDAO = new LivreurDAO();
    private ProduitDAO produitDAO = new ProduitDAO();
    private RestaurantDAO restaurantDAO = new RestaurantDAO();
    private CommandeDAO commandeDAO = new CommandeDAO();
    
    public ServiceMetier() {}
    
    /**
     * Persiste un client dans la base de données, le mail de création erreur
     * est également envoyé par le service en cas de succès/erreur.
     * @param nom Le nom du client
     * @param prenom Le prénom du client
     * @param email L'adresse mail du client
     * @param adresse L'adresse du client
     * @return Client|null Le client crée dans la base de données
     * @throws EmailAlreadyUsedException Execption renvoyée si un client
     * utilise déjà cette adresse email
     * @throws EmptyAttributeException Exception renvoyée si un des attributs est
     * null ou vide
     * @throws InvalidAddressException Exception renvoyée si l'adresse fournie
     * n'a pas permis de calculer les coordonnées GPS du client
     * @throws InvalidEmailFormatException Exception renvoyée si l'adresse e-mail
     * a un format incorrect
     */
    public Client enregistrerClient( 
            String nom, 
            String prenom, 
            String email, 
            String adresse
    ) throws EmailAlreadyUsedException,
             EmptyAttributeException,
             InvalidAddressException,
             InvalidEmailFormatException {
        
        if( !Validator.CheckNotNullAndNotEmpty(nom) ) {
            throw new EmptyAttributeException("Le nom ne doit pas être vide");
        }
        
        if( !Validator.CheckNotNullAndNotEmpty(prenom)) {
            throw new EmptyAttributeException("Le prénom ne doit pas être vide");
        }
        
        if( !Validator.CheckNotNullAndNotEmpty(email) ) {
            throw new EmptyAttributeException("L'adresse mail ne doit pas être vide");
        }
        
        if( !Validator.isMail(email) ) {
            throw new InvalidEmailFormatException("L'adresse email n'est pas correcte");
        }
        
        if( !Validator.CheckNotNullAndNotEmpty(adresse) ) {
            throw new EmptyAttributeException("L'adresse ne doit pas être vide");
        }
                
        Client clientCree = new Client(nom, prenom, email, adresse);
        
        if( !ServiceTechnique.calculerCoordonnees(clientCree) ) {
            throw new InvalidAddressException("L'adresse fournie ne permet pas de calculer les coordonnées GPS.");
        }
        
        JpaUtil.ouvrirTransaction();
        try {
            
            if(null == clientDAO.insert(clientCree)) {
                JpaUtil.annulerTransaction();
                throw new EmailAlreadyUsedException();
            }
            
            JpaUtil.validerTransaction();
            
            ServiceTechnique.envoyerMailConfirmationInscription(clientCree);
            
        } catch (EmailAlreadyUsedException e) {
            throw e;
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            JpaUtil.annulerTransaction();
            ServiceTechnique.envoyerMailEchecInscription(clientCree);
            
        }
        
        return clientCree;
        
    }
    
    /**
     * Recherche un utilisateur possédant le mail "mail". Si aucun client ne 
     * possède le mail "mail" renvoie null
     * @param mail Le mail à rechercher
     * @return Client|null
     */
    public Client authentifierClient( String mail ) {
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
    
    /**
     * Renvoie le client identifié par l'id "id". Si aucun Client ne
     * correspond renvoie null
     * @param id L'identifiant du client
     * @return Client|null
     */
    public Client findClientById( Long id ) {
        
        try {
            return clientDAO.findById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        
    }
    
    /**
     * Renvoie la Commande identifié par l'id "id". Si aucun Commande ne
     * correspond renvoie null
     * @param id L'identifiant de la Commande
     * @return Commande|null
     */
    public Commande findCommandeById( Long id ) {
        
        try {
            return commandeDAO.findById(id);
        } catch ( Exception e ) {
            System.err.println(e.getMessage());
            return null;
        }
        
    }
    
    /**
     * Renvoie le Produit identifié par l'id "id". Si aucun Produit ne
     * correspond renvoie null
     * @param id L'identifiant du Produit
     * @return Produit|null
     */
    public Produit findProduitById( Long id ) {
        
        try {
            return produitDAO.findById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        
    }
    
    /**
     * Renvoie la liste des Restaurant
     * @return List<Restaurant>
     */
    public List<Restaurant> findAllRestaurants() {
        
        try {
            return restaurantDAO.findAll();
        } catch( Exception e) {
            System.err.println(e.getMessage());   
        }
        return new ArrayList<>();
        
    }
    
    /**
     * Renvoie la liste des Restaurant dont le nom commence par "name"
     * @param name Le nom du restaurant cherché
     * @return List<Restaurant>
     */
    public List<Restaurant> searchRestaurantByName( String name ) {
        List<Restaurant> result = new ArrayList<>();
        
        try {
            result = (List<Restaurant>) restaurantDAO.findAll();
        } catch ( Exception e ) {
            System.err.println(e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Renvoie le Restaurant identifié par l'id "id". Si aucun Restaurant ne
     * correspond renvoie null
     * @param id L'identifiant du Restaurant
     * @return Restaurant|null
     */
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
      
    /**
     * Crée une commande vide d'un Client vers un Restaurant. Renvoie la Commande
     * si elle a bien été persistée, sinon renvoie null
     * @param c Le client effectuant la commande
     * @param r Le restaurant dans lequel a été effectuée la commande
     * @return Commande|null
     * @throws InvalidReferenceException Si le client ou le Restaurant fournis
     * en paramètres n'existent pas dans l'unité de persistance
     */
    public Commande creerCommande( Client c, Restaurant r ) throws InvalidReferenceException {
        
        if( !clientDAO.contains(c) ) {
            throw new InvalidReferenceException("Le client n'existe pas");
        }
        
        if( !restaurantDAO.contains(r) ) {
            throw new InvalidReferenceException("Le restaurant n'existe pas");
        }
        
        Commande commande = new Commande(c, r); // Etat en cours
        JpaUtil.ouvrirTransaction();
        commande = commandeDAO.insert(commande);
        if(null == commande) {
            JpaUtil.annulerTransaction();
        } else {
            JpaUtil.validerTransaction();
        }
        return commande;
    }
    
    /**
     * Annule une commande. Une commande déjà validée ne peut pas être annulée.
     * Renvoie true si la commande a bien été annulée, sinon renvoie false.
     * @param c La commande à annuler
     * @return boolean
     */
    public boolean annulerCommande( Commande c ) {
        
        if(commandeDAO.contains(c)) {
            return false;
        }
        
        if( c.getEtat() == Commande.Etat.validee ) {
            return false;
        }
        
        JpaUtil.ouvrirTransaction();
        try {
            
            commandeDAO.remove(c);
            JpaUtil.validerTransaction();
            
            return true;
            
        } catch ( Exception e ) {
            JpaUtil.annulerTransaction();
            return false;
        }
        
    }
    
    /**
     * Renvoit la liste des urls des images d'une liste de restaurant
     * @param restaurants La liste de restaurants pour lesquels on souhaite
     * avoir l'url de leur image
     * @return List<String>
     */
    public List<String> getPictureUrlOf( List<Restaurant> restaurants ) {
        List<String> result = new ArrayList<>();
        
        for( Restaurant r : restaurants ) {
            result.add(ServiceTechnique.getUrlPictureOf(r));
        }
        
        return result;
    }
       
    /**
     * Ajoute une qunatité de 1 pour un produit à une commande. 
     * Renvoie la commande modifiée ou null
     * en cas d'erreur de peristance
     * @param c La commande à laquelle ajouter le produit
     * @param p Le produit à ajouter
     * @return Commande|null
     * @throws InvalidReferenceException Exception levée si la commande ou le
     * produit n'existent pas dans l'unité de peristance
     * @throws InvalidActionException L'exception est levée si on essaie d'ajouter
     * un produit qui n'appartient pas au restaurant qui est rattaché à la commande
     */
    public Commande ajouterProduit( Commande c, Produit p ) throws InvalidReferenceException, InvalidActionException {
        
        if( !commandeDAO.contains(c) ) {
            throw new InvalidReferenceException("La commande n'existe pas dans la base de données");
        }        
        
        if( !produitDAO.contains(p) ) {
            throw new InvalidReferenceException("Le produit n'existe pas dans la base de données");
        }
        
        if( !c.getRestaurant().getProduits().contains(p) ) {
            throw new InvalidActionException("Impossible d'ajouter un produit qui n'appartient pas au restaurant lié à la commande");
        }
        boolean exist = false;
        for( LigneCommande ligneCommande : c.getLignesCommandes() ) {
            if( ligneCommande.getProduit().equals(p) ) {
                ligneCommande.setQuantite(ligneCommande.getQuantite() + 1);
                exist = true;
            }
        }
        
        if( !exist ) {
            c.getLignesCommandes().add(new LigneCommande( c, p ));
        }
        
        JpaUtil.ouvrirTransaction();
        c = commandeDAO.update(c);
        if( null == c) {
            JpaUtil.annulerTransaction();
        } else {
            JpaUtil.validerTransaction();
        }
        
        return c;
    }
    
    /**
     * Enlève une quantité de 1 pour un produit à une commande
     * Renvoie la commande modifiée, renvoie null si la peristance a echouée
     * @param c La commande à laquelle enlever le produit
     * @param p Le produit à enlever
     * @return Commmande|null
     * @throws InvalidReferenceException La commande ou le produit n'existent
     * pas dans l'unité de persistance
     * @throws InvalidActionException L'exception peut être levée si le produit
     * n'appartient pas à la commande ou si sa quantité est déjà égale à 0
     */
    public Commande enleverProduit( Commande c, Produit p ) throws InvalidReferenceException, InvalidActionException {
        
        if( !commandeDAO.contains(c) ) {
            throw new InvalidReferenceException("La commande n'existe pas dans la base de données");
        }        
        
        if( !produitDAO.contains(p) ) {
            throw new InvalidReferenceException("Le produit n'existe pas dans la base de données");
        }
        
        boolean exist = false;
        LigneCommande ligneASupprimee = null;
        for( LigneCommande ligneCommande : c.getLignesCommandes() ) {
            if( ligneCommande.getProduit().equals(p) ) {
                if( ligneCommande.getQuantite() == 0 ) {
                    throw new InvalidActionException("Impossible d'enlever un produit dont la quantité est à 0");
                }
                ligneCommande.setQuantite(ligneCommande.getQuantite() - 1);
                if( ligneCommande.getQuantite() == 0 ) {
                    ligneASupprimee = ligneCommande;
                }
                exist = true;
            }
        }
        
        if( null != ligneASupprimee ) {
            c.getLignesCommandes().remove(ligneASupprimee);
        }
        
        if( !exist ) {
            throw new InvalidActionException("Impossible d'enlever un produit qui n'est pas dans la commande");
        }
        
       JpaUtil.ouvrirTransaction();
        c = commandeDAO.update(c);
        if( null == c) {
            JpaUtil.annulerTransaction();
        } else {
            JpaUtil.validerTransaction();
        }
        
        return c;
        
    }
    
    /**
     * Valide la commande. Renvoie Livraison si la commande a bien été validée
     * et que la livraison a bien été attribuée à un livreur, sinon renvoie null
     * @param c La commande à valider
     * @return Livraison|null
     * @throws InvalidReferenceException La commande n'existe pas dans l'unité
     * de persistance
     * @throws InvalidActionException L'exception est levée si l'utilisateur 
     * essaie de valider une commande vide (toutes les quantités à 0 ou 
     * aucune ligne de commande dans la commande)
     */
    public Livraison validerCommande( Commande c ) throws InvalidReferenceException, InvalidActionException {
        
        if( !commandeDAO.contains(c) ) {
            throw new InvalidReferenceException("La commande n'existe pas");
        }
        
        Livraison livraison = c.valider();
        if( null == livraison ) {
            throw new InvalidActionException("Il n'est pas possible de valider une commande vide");
        }
        
        JpaUtil.ouvrirTransaction();
        Livreur livreur = ServiceTechnique.findMeilleurLivreurPour(livreurDAO, livraison);
        
        if( null == livreur ) {
            return null;
        }
        
        c.setEtat(Commande.Etat.validee);
        livraison.setLivreur(livreur);
        livraison.setEtat(Livraison.Etat.en_cours);
        livreur.getLivraisons().add(livraison);
        livreur.setLibre(false);
        
        //-------------------------------------
        livraison = livraisonDAO.insert(livraison);
        Commande majCommande = commandeDAO.update(c);
        Livreur majLivreur = livreurDAO.update(livreur);
        //--------------------------------------
        if( null != livraison && null != majCommande && null != majLivreur ) {
            JpaUtil.validerTransaction();
            
            if( livreur instanceof LivreurPersonne ) {
                ServiceTechnique.envoyerMailAssignationLivraison(livraison, ((LivreurPersonne)livreur));
            }
            
        } else {
            JpaUtil.annulerTransaction();
        }
        
        return livraison;
        
    }
    
    /**
     * Valide la Livraison avec la date système actuelle. Renvoie la Livraison
     * modifiée en cas de réussite, null sinon
     * @param l La Livraison à valider
     * @return Livraison|null
     * @throws InvalidReferenceException La livraison n'existe pas dans l'unité
     * de persistance
     * @throws InvalidActionException L'exception est levée si la date de fin
     * de livraison est inférieur ou égale à la date de début de livraison. Elle
     * peut être également levée en cas de demande invalide validation (livraison
     * non attribuée ou livraison déjà validée)
     */
    public Livraison validerLivraison( Livraison l ) throws InvalidReferenceException, InvalidActionException {
        
        return validerLivraison(l, Calendar.getInstance().getTime());
        
    }

    /**
     * Renvoie le Livreur identifié par l'id "id". Si aucun Livreur ne
     * correspond renvoie null
     * @param id L'identifiant du Livreur
     * @return Livreur|null
     */
    public Livreur findLivreurById( Long id ) {

        return livreurDAO.findById(id);

    }
    
    /**
     * Valide la Livraison avec la date fournie en paramètres. Renvoie la Livraison
     * modifiée en cas de réussite, null sinon
     * @param l La Livraison à valider
     * @param date La date à laquelle a été effectuée la livraison
     * @return Livraison|null
     * @throws InvalidReferenceException La livraison n'existe pas dans l'unité
     * de persistance
     * @throws InvalidActionException L'exception est levée si la date de fin
     * de livraison est inférieur ou égale à la date de début de livraison. Elle
     * peut être également levée en cas de demande invalide validation (livraison
     * non attribuée ou livraison déjà validée)
     */
    public Livraison validerLivraison( Livraison l, Date date ) throws InvalidReferenceException, InvalidActionException {
        
        if( !livraisonDAO.contains(l) ) {
            throw new InvalidReferenceException("La livraison n'existe pas");
        }
        
        if( Livraison.Etat.en_cours != l.getEtat() || null == l.getDateDebut() ) {
            throw new InvalidActionException("La livraison ne peut pas être validée");
        }
        
        if( null != l.getDateFin() ) {
            throw new InvalidActionException("La livraison a déjà été validée");
        }

        if( date.compareTo( l.getDateDebut() ) <= 0 ) {
            throw new InvalidActionException("La date de validation doit être postérieure à la date de début de livraison");
        }
        
        l.setEtat(Livraison.Etat.livree);
        l.setDateFin( date );
        l.getLivreur().setLibre(true);
        
        JpaUtil.ouvrirTransaction();
        l = livraisonDAO.update(l);
        if(null == l) {
            JpaUtil.annulerTransaction();
        } else {
            JpaUtil.validerTransaction();
        }
        return l;
        
    }
    
    /**
     * Renvoie la liste des livraisons effectuées par les drônes
     * @return List<Livraison>
     */
    public List<Livraison> findLivraisonsParDrone() {
        return livraisonDAO.findLivraisonsParDrone();
    }
    
    /**
     * Renvoie la liste des clients qui ont une livraison en cours
     * @return List<Client>
     */
    public List<Client> findClientsEnAttenteDeLivraison() {
        return clientDAO.findClientsAvecLivraisonEnCours();    
    }
    
    /**
     * Renvoie la liste des clients qui N'ont PAS de livraison en cours
     * @return List<Client>
     */
    public List<Client> findClientsInactifs() {
        return clientDAO.findClientsInactifs();
    }
    
    /**
     * Renvoie la liste des livreurs en cours de livraison
     * @return List<Livreur>
     */
    public List<Livreur> findLivreursOccupes() {
        return livreurDAO.findNonLibres();
    }
    
    /**
     * Renvoie la liste des livreurs disponibles
     * @return List<Livreur>
     */
    public List<Livreur> findLivreursLibres() {
        return livreurDAO.findLibres();
    }
    
    
    
    
}
