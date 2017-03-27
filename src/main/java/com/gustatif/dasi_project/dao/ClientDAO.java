package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.modele.Livraison;
import com.gustatif.dasi_project.metier.modele.Livreur;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Le Data Access object chargé de manipuler les Client
 * @author Loic
 */
public class ClientDAO extends CRUDDAo<Client>{

    /**
     * Constructeur par défaut
     */
    public ClientDAO() {
        super();
    }
    
    /**
     * Constructeur avec entity manager. Utilise l'entity manager pour effectuer
     * la persistance
     * @param em Entity Manager
     */
    public ClientDAO(EntityManager em) {
        super(em);
    }

    /**
     * Renvoie le nom de la classe gérée par ClientDAO
     * @return Class<Client> la classe gérée par le DAO
     */
    @Override
    protected Class<Client> getEntityClass() {
        return Client.class;
    }
    
    /**
     * Renvoie l'utilisateur identifié par l'email "Email". Si aucun Client
     * n'est trouvé, renvoie null
     * @param email Un email sous forme de chaine de caractères
     * @return Client|null
     */
    public Client findByEmail( String email ) {
        Objects.requireNonNull(email);
        TypedQuery<Client> findByEmailQuery = em.createQuery("Select c From Client c Where c.mail = :email", Client.class);
        Client client = null;
        try {
            client = (Client) findByEmailQuery.setParameter("email", email).getSingleResult();
        } catch( NoResultException noResult ) {
            return null;
        } catch(Exception e) {
            System.err.println(e);
        }
        return client;
    }
    
    /**
     * Persiste le Client si aucun Client ne possède son adresse email. Si un
     * client possède déjà cette adresse email le Client n'est pas persisté
     * et la méthode renvoie null. Si le Client est persisté, renvoie le Client
     * @param c Le client à persisté
     * @return Client|null
     */
    @Override
    public Client insert(Client c) {
        if( findByEmail( c.getMail() ) != null ) {
            return null;
        }
        em.persist(c);
        return c;
    } 
    
    /**
     * Renvoie la liste de client n'ayant pas de livraisons en cours
     * @return List<Client>
     */
    public List<Client> findClientsInactifs() {
        Query query = em.createQuery(
                    "Select c From Client c Where c not in ( Select Distinct(client) " +
                            "From Client client " +
                            "Left Join Commande commande " +
                            "Left Join Livraison livraison " +
                            "Where livraison.etat = :etat and commande.client = client and livraison.commande = commande ) ", Client.class);
        List<Client> clients = new ArrayList<>();
         try {
            clients = (List<Client>) query.setParameter("etat", Livraison.Etat.en_cours).getResultList();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
        return clients;
        
    }
    
    /**
     * Renvoie la liste de Client avec une livraison en cours
     * @return List<Client>
     */
    public List<Client> findClientsAvecLivraisonEnCours() {
        
        Query query = em.createQuery(
                    "Select Distinct(client) " +
                      "From Client client " +
                      "Left Join Commande commande " +
                      "Left Join Livraison livraison " +
                      "Where livraison.etat = :etat and commande.client = client and livraison.commande = commande", Client.class);
        List<Client> clients = new ArrayList<>();
         try {
            clients = (List<Client>) query.setParameter("etat", Livraison.Etat.en_cours).getResultList();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
        return clients;
    }
    
}
