package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Commande;
import javax.persistence.EntityManager;

/**
 * Le Data Access Object chargé de manipuler les Commande
 * @author Loic
 */
public class CommandeDAO extends CRUDDAo<Commande> {

    /**
     * Renvoie ln nom de la classe gérée par le CommandeDAO
     * @return Class<Commande> la classe gérée par le DAO
     */
    @Override
    protected Class<Commande> getEntityClass() {
        return Commande.class;
    }

    /**
     * Constructeur avec entity manager. Utilise l'entity manager pour effectuer
     * la persistance
     * @param em Entity Manager
     */
    public CommandeDAO(EntityManager em) {
        super(em);
    }
    
    /**
     * Constructeur par défaut
     */
    public CommandeDAO() {
    }   
    
}
