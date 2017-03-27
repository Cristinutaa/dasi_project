/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe correspondant à un drone de livraison
 * @author carhiliuc
 */
@Entity
public class LivreurDrone extends Livreur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /**
     * La vitesse moyenne de la drone
     */
    private double vitesseMoyenne;

    /**
     * Constructeur par défaut
     */
    public LivreurDrone() {
    }

    /**
     * Constructeur avec vitesseMoyenne
     * @param vitesseMoyenne La vitesse moyenne du drône
     */
    public LivreurDrone(double vitesseMoyenne) {
        this.vitesseMoyenne = vitesseMoyenne;
    }

    public LivreurDrone(double vitesseMoyenne, double chargeMaximale, String adresse_base) {
        super(chargeMaximale, adresse_base);
        this.vitesseMoyenne = vitesseMoyenne;
    }

    public double getVitesseMoyenne() {
        return vitesseMoyenne;
    }

    public void setVitesseMoyenne(double vitesseMoyenne) {
        this.vitesseMoyenne = vitesseMoyenne;
    }
    
    @Override
    public String toString() {
        return "LivreurDrone{" + "vitesseMoyenne=" + vitesseMoyenne + '}';
    }
       
}
