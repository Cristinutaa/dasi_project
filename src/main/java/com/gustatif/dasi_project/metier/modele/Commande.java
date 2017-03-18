package com.gustatif.dasi_project.metier.modele;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Commande extends Model {
    
    public enum Etat {
        en_cours,
        validee
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    protected Client client;
    
    @ManyToOne
    protected Restaurant restaurant;
    protected Etat etat;
    
    @OneToOne
    protected Livraison livraison;
    
    protected List<LigneCommande> lignesCommandes;
    
    protected Commande() {}

    public Commande(Client client, Restaurant restaurant) {
        this.client = client;
        this.restaurant = restaurant;
        this.lignesCommandes = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<LigneCommande> getLignesCommandes() {
        return lignesCommandes;
    }

    public void setLignesCommandes(List<LigneCommande> lignesCommandes) {
        this.lignesCommandes = lignesCommandes;
    }
    
    public Livraison valider() {
        return new Livraison( this );
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    
}
