package com.gustatif.dasi_project.metier.modele;

import com.gustatif.dasi_project.config.Config;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe correspondant à un livreur à vélo
 * @author Loic
 */
@Entity
public class LivreurPersonne extends Livreur{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /**
     * Le nom de la personne
     */
    private String nom;
    
    /**
     * Le prenom de la personne
     */
    private String prenom;

    /**
     * Constructeur par défaut
     */
    public LivreurPersonne() {
        
    }
    
    /**
     * Constructeur avec nom et prénom du livreur
     * @param nom Le nom de famille du livreur
     * @param prenom Le prénom du livreur
     */
    public LivreurPersonne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    /**
     * Constructeur avec nom, prenom, charge maximal et adresse de départ
     * @param nom Le nom de famille du livreur
     * @param prenom Le prénom du livreur
     * @param charge_maximale Le charge maximale supportée par le livreur
     * @param adresse_base L'adresse de départ du livreur
     */
    public LivreurPersonne(String nom, String prenom, double charge_maximale, String adresse_base) {
        super(charge_maximale, adresse_base);
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "LivreurPersonne{" + "nom=" + nom + ", prenom=" + prenom + '}';
    }
    
   
    public String getMail() {
        return this.prenom + "." + this.nom + "@" + Config.DOMAIN_NAME;
    }
    
}
