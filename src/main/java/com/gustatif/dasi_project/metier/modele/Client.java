package com.gustatif.dasi_project.metier.modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
public class Client extends Model implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = 0l;
    private String nom;
    private String prenom;
    private String mail;
    private String adresse;
    private Double longitude;
    private Double latitude;

    protected Client() {}
    
    public Client(String nom, String prenom, String mail, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse = adresse;
        this.longitude = null;
        this.latitude = null;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
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

    public Client setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public LatLng getLocation() {
        return new LatLng(latitude, longitude);
    }
    
    public Client setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public Client setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public Client setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public Client setLatitudeLongitude(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        return this;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", adresse=" + adresse + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }

}