package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Client;
import com.gustatif.dasi_project.metier.modele.Livraison;
import com.gustatif.dasi_project.metier.modele.LivreurDrone;
import com.gustatif.dasi_project.metier.modele.LivreurPersonne;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class LivraisonDAO extends CRUDDAo<Livraison> {

    public LivraisonDAO() {
        super();
    }
    
    public LivraisonDAO(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Livraison> getEntityClass() {
        return Livraison.class;
    }

    public List<Livraison> findByClient( Client client ) {
        return new ArrayList<>();
    }
    
    public Livraison findLastOfClient( Client client ) {
        Livraison l = null;
        try {
            Query q = em.createQuery("Select l From Livraison l where livraison.client = :client OrderBy livraison.dateDebut DESC");
            l = (Livraison) q.getSingleResult();
        } catch( Exception e ) {}
        return l;
    }
    
    public List<Livraison> findNonLivrees() {
        List<Livraison> livraisons = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT l From Livraison l where livraison.livree = FALSE");
            livraisons = (List<Livraison>) q.getResultList();
        }
        catch(Exception e) {}
        return livraisons;
    }
    
    public List<Livraison> findLivreesParDrone() {
        
        List<Livraison> result = findNonLivrees();
        
        result.removeIf(new Predicate<Livraison>() {
            @Override
            public boolean test(Livraison t) {
                return ( t.getLivreur() instanceof LivreurPersonne );
            }
        });
        
        return result;
        
    }
    
}
