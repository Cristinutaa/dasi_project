package com.gustatif.dasi_project.metier.modele;

import java.util.Date;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livraison extends Model {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateDebut;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateFin;
    protected boolean livree;
    
    @ManyToMany
    protected Map<Produit, Integer> produits;
    
    @ManyToOne
    protected Client client;
    
    @ManyToOne
    protected Restaurant restaurant;
    
    public Livraison() {}

    public Long getId() {
        return id;
    }
    
    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isLivree() {
        return livree;
    }

    public void setLivree(boolean livree) {
        this.livree = livree;
    }

    public Map<Produit, Integer> getProduits() {
        return produits;
    }   
    
    public boolean isConfirmee() {
        return this.dateFin != null;
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
    
}
