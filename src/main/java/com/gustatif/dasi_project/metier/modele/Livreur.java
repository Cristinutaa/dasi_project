/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.metier.modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author carhiliuc
 */
@Entity
public class Livreur extends Model implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LatLng location;
    private double charge_maximale;
    private boolean libre;
    private String adresse_base;
    
    public Livreur() {
    }
    
    public Livreur(double charge_maximale, String adresse_base) {
        this.charge_maximale = charge_maximale;
        this.adresse_base = adresse_base;
    }

    public Long getId() {
        return id;
    }

    public LatLng getLocation() {
        return location;
    }

    public double getCharge_maximale() {
        return charge_maximale;
    }

    public boolean isLibre() {
        return libre;
    }

    public String getAdresse_base() {
        return adresse_base;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public void setCharge_maximale(double charge_maximale) {
        this.charge_maximale = charge_maximale;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    public void setAdresse_base(String adresse_base) {
        this.adresse_base = adresse_base;
    }

    @Override
    public String toString() {
        return "Livreur{" + "id=" + id + ", location=" + location + ", charge_maximale=" + charge_maximale + ", libre=" + libre + ", adresse_base=" + adresse_base + '}';
    }
    
}
