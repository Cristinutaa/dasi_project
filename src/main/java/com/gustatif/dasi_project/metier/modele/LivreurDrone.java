/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.metier.modele;

import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.gustatif.dasi_project.util.GeoTest;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author carhiliuc
 */
@Entity
public class LivreurDrone extends Livreur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private double vitesseMoyenne;

    public LivreurDrone() {
    }

    
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
    
    /*
        Explications dans la classe Livreur
    */
    @Override
    public double getDistance(LatLng from, LatLng... steps){
        GeoTest temp = new GeoTest(); 
        if (vitesseMoyenne == 0){
            return  Double.MAX_VALUE;
        }
        double distance = temp.getTripDurationOrDistance(TravelMode.UNKNOWN, false, this.getLocation(), from, steps)/(vitesseMoyenne/60);
        return distance;
    }

    @Override
    public String toString() {
        return "LivreurDrone{" + "vitesseMoyenne=" + vitesseMoyenne + '}';
    }
       
}
