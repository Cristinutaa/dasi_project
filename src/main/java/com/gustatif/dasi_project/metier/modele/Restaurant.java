package com.gustatif.dasi_project.metier.modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Classe représentant un Restaurant
 * @author Loic
 */
@Entity
public class Restaurant extends Model implements Serializable {
    
    /**
     * Identifiant unique du restaurant
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * Nom du restaurant
     */
    private String denomination;
    
    /**
     * Description du restaurant
     */
    private String description;
    
    /**
     * Adresse du restaurant
     */
    private String adresse;
    
    /**
     * Attribut longitude de la position GPS du restaurant
     */
    private Double longitude;
    
    /**
     * Attribut latitude de la position GPS du restaurant
     */
    private Double latitude;

    /**
     * Liste de produits vendu par le restaurant
     */
    @OneToMany
    private List<Produit> produits;
    
    /**
     * Constructeur par défaut
     */
    protected Restaurant() {
        this.produits = new ArrayList<>();
    }

    /**
     * Constructeur renseigné
     * @param denomination Le nom du restaurant
     * @param description La description du restaurant
     * @param adresse L'adresse du restaurant
     */
    public Restaurant(String denomination, String description, String adresse) {
        this.denomination = denomination;
        this.description = description;
        this.adresse = adresse;
        this.longitude = null;
        this.latitude = null;
        this.produits = new ArrayList<>();
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

    public String getAdresse() {
        return adresse;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public LatLng getPosition() {
        return new LatLng(latitude, longitude);
    }
    
    public List<Produit> getProduits() {
        return produits;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setLatitudeLongitude(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + id + ", denomination=" + denomination + ", description=" + description + ", adresse=" + adresse + ", longitude=" + longitude + ", latitude=" + latitude + ", produits=" + produits + '}';
    }

}
