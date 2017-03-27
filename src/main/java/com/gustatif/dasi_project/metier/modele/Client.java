package com.gustatif.dasi_project.metier.modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe représentant un Client
 * @author Loic
 */
@Entity
public class Client extends Model implements Serializable {
    
    /**
     * Identifiant du client
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    /**
     * Nom du client
     */
    private String nom;
    
    /**
     * Prénom du client
     */
    private String prenom;
    
    /**
     * Adresse mail du client
     */
    private String mail;
    
    /**
     * Adresse postale du client
     */
    private String adresse;
    
    /**
     * Attribut longitude de la position GPS de l'adresse du client
     */
    private Double longitude;
    
    /**
     * Attribut latitude de la position GPS de l'adresse du client
     */
    private Double latitude;

    /**
     * Constructeur par défaut
     */
    protected Client() {}
    
    /**
     * Constructeur d'un Client
     * @param nom Le nom du client
     * @param prenom Le prénom du client
     * @param mail L'adresse mail du client (doit être une adresse mail valide)
     * @param adresse L'adresse postale du client (doit être une adresse
     * postale valide)
     */
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