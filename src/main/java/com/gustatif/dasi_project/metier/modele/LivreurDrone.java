/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.metier.modele;

/**
 *
 * @author carhiliuc
 */
public class LivreurDrone extends Livreur{
    private double vitesse_moyenne;

    public LivreurDrone() {
    }

    
    public LivreurDrone(double vitesse_moyenne) {
        this.vitesse_moyenne = vitesse_moyenne;
    }

    public LivreurDrone(double vitesse_moyenne, double charge_maximale, String adresse_base) {
        super(charge_maximale, adresse_base);
        this.vitesse_moyenne = vitesse_moyenne;
    }

    public double getVitesse_moyenne() {
        return vitesse_moyenne;
    }

    public void setVitesse_moyenne(double vitesse_moyenne) {
        this.vitesse_moyenne = vitesse_moyenne;
    }

    @Override
    public String toString() {
        return "LivreurDrone{" + "vitesse_moyenne=" + vitesse_moyenne + '}';
    }
       
}
