package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.LigneLivraison;


public class LigneLivraisonDAO extends CRUDDAo<LigneLivraison> {

    @Override
    protected Class<LigneLivraison> getEntityClass() {
        return LigneLivraison.class;
    }
    
}
