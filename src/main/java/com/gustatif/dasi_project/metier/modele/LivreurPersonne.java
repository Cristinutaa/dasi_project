/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author carhiliuc
 */
@Entity
public class LivreurPersonne extends Livreur{
    private String nom;
    private String prenom;

    public LivreurPersonne() {
        
    }
    
    public LivreurPersonne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
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
    
}
