package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.LigneCommande;
import javax.persistence.EntityManager;

/**
 * Le Data Access object chargé de manipuler les LigneCommande
 * @author Loic
 */
public class LigneCommandeDAO extends CRUDDAo<LigneCommande> {

    /**
     * Constructeur par défaut
     */
    public LigneCommandeDAO() {
        super();
    }
    
    /**
     * Constructeur avec entity manager. Utilise l'entity manager pour effectuer
     * la persistance
     * @param em Entity Manager
     */
    public LigneCommandeDAO(EntityManager em) {
        super(em);
    }

    /**
     * Renvoie le nom de la classe gérée par LigneCommandeDAO
     * @return Class<LigneCommande> la classe gérée par le DAO
     */
    @Override
    protected Class<LigneCommande> getEntityClass() {
        return LigneCommande.class;
    }
    
}
