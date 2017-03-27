package com.gustatif.dasi_project.metier.modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * Classe correspondant à un livreur
 * @author carhiliuc
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Livreur extends Model implements Serializable {
   
    /**
     * Identifiant unique du livreur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * La longitude de la position de livreur
     */
    private double longitude;
    
    /**
     * La latitude de la position de livreur
     */
    private double latitude;
    
    /**
     * La charge maximale que peut être portée par le livreur
     */
    private double chargeMaximale;
    
    /**
     * Le statut de livreur: libre ou occupé
     */
    private boolean libre = true;
    
    /**
     * Adresse de départ du livreurs
     */
    private String adresseBase;
    @OneToMany(mappedBy = "livreur")
    
    /**
     * Ensemble de livraisons effectuée par le livreurs
     */
    private List<Livraison> livraisons = new ArrayList<>();
    
    /**
     * Constructeur par défaut
     */
    public Livreur() {
    }

    /**
     * Constructeur renseigné
     * @param charge_maximale La charge maximal supportée par le livreur
     * @param adresse_base L'adresse de départ du livreur
     */
    public Livreur(double charge_maximale, String adresse_base) {
        this.chargeMaximale = charge_maximale;
        this.adresseBase = adresse_base;
       
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
    
    /**
     * Renvoie les coordonnées de livreur dans le format LatLng
     * @return LatLng - les coordonnées de livreur
     */
    public LatLng getLocation() {
        return new LatLng(latitude,longitude);
    }

    public double getChargeMaximale() {
        return chargeMaximale;
    }

    public boolean isLibre() {
        return libre;
    }

    public String getAdresseBase() {
        return adresseBase;
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

    public void setChargeMaximale(double chargeMaximale) {
        this.chargeMaximale = chargeMaximale;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    public void setAdresseBase(String adresse_base) {
        this.adresseBase = adresse_base;
    }
    

    @Override
    public String toString() {
        return "Livreur{" + "id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", chargeMaximale=" + chargeMaximale + ", libre=" + libre + ", adresse_base=" + adresseBase + '}';
    }

    public List<Livraison> getLivraisons() {
        return livraisons;
    }

    public void setLivraisons(List<Livraison> livraisons) {
        this.livraisons = livraisons;
    }

    
}
