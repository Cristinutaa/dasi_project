/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.metier.modele;

import com.google.maps.model.LatLng;
import com.gustatif.dasi_project.util.GeoTest;
import javax.persistence.Entity;

/**
 *
 * @author carhiliuc
 */
@Entity
public class LivreurDrone extends Livreur{
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
    
     public double getDistance(LatLng from, LatLng... steps){
        GeoTest temp = new GeoTest(); 
        return temp.getFlightDistanceInKm(this.getLocation(), from);
    }

    @Override
    public String toString() {
        return "LivreurDrone{" + "vitesseMoyenne=" + vitesseMoyenne + '}';
    }
       
}
