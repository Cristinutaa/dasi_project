package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.LigneCommande;
import javax.persistence.EntityManager;


public class LigneLivraisonDAO extends CRUDDAo<LigneCommande> {

    public LigneLivraisonDAO() {
        super();
    }
    
    public LigneLivraisonDAO(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<LigneCommande> getEntityClass() {
        return LigneCommande.class;
    }
    
}
