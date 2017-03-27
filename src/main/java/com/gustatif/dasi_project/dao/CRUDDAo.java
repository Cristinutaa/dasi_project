package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * CRUDDAo implémente les fonctions de base pour les DAO
 * @author Loic
 * @param <T> Le type de modèle
 */
abstract public class CRUDDAo<T extends Model> {
    
    /**
     * L'entity manager permettant au DAO de communiquer avec la base de données
     */
    protected EntityManager em;
    
    /**
     * Renvoie le nom de classe gérée par le DAO
     * @return Class<T> Le nom de la classe de T
     */
    abstract protected Class<T> getEntityClass();
    
    /**
     * Crée un CRUDDao avec l'entity manager em
     * @param em L'entity manager a utilisé par le DAO
     */
    public CRUDDAo( EntityManager em ) {
        this.em = em;
    }
    
    /**
     * Créer un CRUDDAO avec l'enity manager par defaut
     */
    public CRUDDAo() {
        this.em = JpaUtil.obtenirEntityManager();
        if( null == this.em ) {
            throw new RuntimeException("JpaUtil.creerEntityManager() must be call before the creation of a DAO");
        }
    }
    
    /**
     * Persiste l'object e
     * @param e L'objet a persister
     * @return T L'objet qui a été persisté
     */
    public T insert( T e ) {
        em.persist(e);
        em.flush();
        return e;
    }
   
    /**
     * Met à jour l'objet e
     * @param e L'objet à mettre à jour
     * @return T L'objet qui a été mis à jour
     */
    public T update( T e ) {
        return (T) em.merge(e);
    }
    
    /**
     * Renvoie l'objet ayant l'id 'id'. Si aucun objet n'est trouvé, renvoie null
     * @param id L'identifiant unique de l'objet
     * @return T/null
     */
    public T findById(long id) {
        T t = null;
        try {
            t = (T) em.find(getEntityClass(), id);
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return t;
    }
    
    /**
     * Renvoie la liste des objets
     * @return List<T>
     * @throws Exception 
     */
    public List<T> findAll() {
        List<T> models = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT m FROM " + getEntityClass().getSimpleName() + " m");
            models = (List<T>) q.getResultList();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            return new ArrayList<T>();
        }
        return models;
    }
    
    /**
     * Supprime l'élément e de la base de persistance
     * @param e L'élément à supprimer
     */
    public void remove( T e ) {
        em.remove(e);
    }
    
    /**
     * Renvoie si l'élément de persistance contient l'élément e
     * @param e L'élement dont on doit vérifier la présence
     * @return boolean
     */
    public boolean contains( T e ) {
        
        if( null == e) {
            return false;
        }
        Query q = em.createQuery("Select m FROM " + getEntityClass().getSimpleName() + " m Where m = :object", getEntityClass());
        return q.setParameter("object", e).getResultList().size() > 0;
        
    }
    
    
}
