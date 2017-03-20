/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.metier.modele;

import com.google.maps.model.LatLng;
import com.gustatif.dasi_project.util.GeoTest;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author carhiliuc
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Livreur extends Model implements Serializable {
    
    public enum Etat {
        occupe,
        libre,
        inactif
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double longitude;
    private double latitude;
    private double chargeMaximale;
    private boolean libre = true;
    private String adresse_base;
    private Etat etat;
    
    public Livreur() {
    }

    public Livreur(double charge_maximale, String adresse_base) {
        this.chargeMaximale = charge_maximale;
        this.adresse_base = adresse_base;
    }
    
    public Long getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public LatLng getLocation() {
        return new LatLng(latitude,longitude);
    }

    public double getChargeMaximale() {
        return chargeMaximale;
    }

    public boolean isLibre() {
        return libre;
    }

    public String getAdresse_base() {
        return adresse_base;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLocation(LatLng location){
        this.longitude = location.lng;
        this.latitude = location.lat;
    }

    public void setCharge_maximale(double chargeMaximale) {
        this.chargeMaximale = chargeMaximale;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    public void setAdresse_base(String adresse_base) {
        this.adresse_base = adresse_base;
    }
    
    abstract public double getDistance(LatLng from, LatLng... steps);

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Livreur{" + "id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", chargeMaximale=" + chargeMaximale + ", libre=" + libre + ", adresse_base=" + adresse_base + '}';
    }

    
}
