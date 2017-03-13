package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Client;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ClientDAO extends CRUDDAo<Client>{

    @Override
    protected Class<Client> getEntityClass() {
        return Client.class;
    }
    
    public Client findByEmail( String email ) {
        Objects.requireNonNull(email);
        
        EntityManager em = JpaUtil.obtenirEntityManager();
        TypedQuery<Client> findByEmailQuery = em.createQuery("Select c From Client c Where c.mail = :email", Client.class);
        Client client = null;
        try {
            client = (Client) findByEmailQuery.setParameter("email", email).getSingleResult();
        }
        catch(Exception e) {
            throw e;
        }
        return client;
    }
    
    @Override
    public Client insert(Client c) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        if( findByEmail( c.getMail() ) != null ) {
            return null;
        }
        em.persist(c);
        
        return c;
    }
    
    
    
    
}
