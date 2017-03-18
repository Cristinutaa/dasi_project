package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Commande;
import javax.persistence.EntityManager;

public class CommandeDAO extends CRUDDAo<Commande> {

    @Override
    protected Class<Commande> getEntityClass() {
        return Commande.class;
    }

    public CommandeDAO(EntityManager em) {
        super(em);
    }

    public CommandeDAO() {
    }   
    
}
