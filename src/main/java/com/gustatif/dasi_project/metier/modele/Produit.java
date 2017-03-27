package com.gustatif.dasi_project.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe correspondant à un produit
 * @author Loic
 */
@Entity
public class Produit extends Model implements Serializable {
    
    /**
     * Identifiant unique du produit
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * La dénomination du produit (nom)
     */
    private String denomination;
    
    /**
     * La description du produit
     */
    private String description;
    
    /**
     * Le poids unitaire du produits en kg
     */
    private Double poids;
    
    /**
     * Le prix unitaire du produit en €uro
     */
    private Double prix;

    /**
     * Constructeur par défaut
     */
    public Produit() {
    }

    /**
     * Constructeur de produit
     * @param denomination Le nom du produit
     * @param description La description du produit
     * @param poids Le poids du produit en kg
     * @param prix Le prix du produit en €uro
     */
    public Produit(String denomination, String description, Double poids, Double prix) {
        this.denomination = denomination;
        this.description = description;
        this.poids = poids;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public String getDenomination() {
        return denomination;
    }

    public String getDescription() {
        return description;
    }

    public Double getPoids() {
        return poids;
    }

    public Double getPrix() {
        return prix;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", denomination=" + denomination + ", description=" + description + ", poids=" + poids + ", prix=" + prix + '}';
    }

}
