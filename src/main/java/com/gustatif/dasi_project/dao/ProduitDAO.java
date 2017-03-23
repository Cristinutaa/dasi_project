package com.gustatif.dasi_project.dao;

import javax.persistence.EntityManager;
import com.gustatif.dasi_project.metier.modele.Produit;

public class ProduitDAO extends CRUDDAo<Produit>{
    
    public ProduitDAO() {
        super();
    }
    
    public ProduitDAO( EntityManager em ) {
        super(em);
    }

    @Override
    protected Class<Produit> getEntityClass() {
        return Produit.class;
    }
}
