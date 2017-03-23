package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.LigneCommande;
import javax.persistence.EntityManager;


public class LigneCommandeDAO extends CRUDDAo<LigneCommande> {

    public LigneCommandeDAO() {
        super();
    }
    
    public LigneCommandeDAO(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<LigneCommande> getEntityClass() {
        return LigneCommande.class;
    }
    
}
