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

public class ClientDAO extends CRUDDAo<Client>{

    public ClientDAO() {
        super();
    }
    
    public ClientDAO(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Client> getEntityClass() {
        return Client.class;
    }
    
    public Client findByEmail( String email ) {
        Objects.requireNonNull(email);
        TypedQuery<Client> findByEmailQuery = em.createQuery("Select c From Client c Where c.mail = :email", Client.class);
        Client client = null;
        try {
            client = (Client) findByEmailQuery.setParameter("email", email).getSingleResult();
        } catch( NoResultException noResult ) {
            return null;
        }
        catch(Exception e) {
            throw e;
        }
        return client;
    }
    
    @Override
    public Client insert(Client c) {
        if( findByEmail( c.getMail() ) != null ) {
            return null;
        }
        em.persist(c);
        return c;
    } 
    
    public List<Client> findClientsInactifs() {
        Query query = em.createQuery(
                    "Select c From Client c Where c not in ( Select Distinct(client) " +
                    "From Livraison l Inner Join l.commande c Inner Join c.client client Where l.etat = :etat )", Client.class);
        List<Client> clients = new ArrayList<>();
         try {
            clients = (List<Client>) query.setParameter("etat", Livraison.Etat.en_cours).getResultList();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
        return new ArrayList<>();
        
    }
    
    public List<Client> findClientsAvecLivraisonEnCours() {
        
        Query query = em.createQuery(
                    "Select Distinct(client) " +
                    "From Livraison l Inner Join l.commande c Inner Join c.client client Where l.etat = :etat", Client.class);
        List<Client> clients = new ArrayList<>();
         try {
            clients = (List<Client>) query.setParameter("etat", Livraison.Etat.en_cours).getResultList();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
        return new ArrayList<>();
    }
    
}
