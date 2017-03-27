package com.gustatif.dasi_project.dao;

import javax.persistence.EntityManager;
import com.gustatif.dasi_project.metier.modele.Produit;

/**
 * Le Data Access object chargé de manipuler les Produit
 * @author Loic
 */
public class ProduitDAO extends CRUDDAo<Produit>{
    
    /**
     * Constructeur par défaut
     */
    public ProduitDAO() {
        super();
    }
    
    /**
     * Constructeur avec entity manager. Utilise l'entity manager pour effectuer
     * la persistance
     * @param em Entity Manager
     */
    public ProduitDAO( EntityManager em ) {
        super(em);
    }
    
    /**
     * Renvoie le nom de la classe gérée par ProduitDAO
     * @return Class<Produit> la classe gérée par le DAO
     */
    @Override
    protected Class<Produit> getEntityClass() {
        return Produit.class;
    }
}
