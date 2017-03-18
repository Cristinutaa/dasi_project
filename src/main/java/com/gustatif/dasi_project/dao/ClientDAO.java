package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Client;
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
    
}
