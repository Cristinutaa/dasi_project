package com.gustatif.dasi_project.metier.modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
        
    @ManyToOne
    protected Client client;
    
    @ManyToOne
    protected Restaurant restaurant;
    
    @OneToMany(mappedBy = "livraison")
    protected List<LigneLivraison> lignesLivraisons = new ArrayList<>();
    
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

    public List<LigneLivraison> getLignesLivraisons() {
        return lignesLivraisons;
    }

    protected void setLignesLivraisons(List<LigneLivraison> lignesLivraisons) {
        this.lignesLivraisons = lignesLivraisons;
    }
    
    public Livraison ajouterProduit( Produit p ) {
        
        LigneLivraison ligneLivraison = null;
        
        for( LigneLivraison ligneLivraisonCourant : lignesLivraisons ) {
            if( ligneLivraisonCourant.getProduit().equals(p) ) {
                ligneLivraison = ligneLivraisonCourant;
            }
        }
       
        if( ligneLivraison == null ) {
            ligneLivraison = new LigneLivraison();
            ligneLivraison.setLivraison(this);
            ligneLivraison.setProduit(p);
            ligneLivraison.setQuantite(1);
            lignesLivraisons.add(ligneLivraison);
        } else {
            ligneLivraison.setQuantite( ligneLivraison.getQuantite() + 1 );
        }
        
        return this;
    }
    
    public Livraison supprimerProduit( Produit p ) {
        
        for( LigneLivraison ligneLivraisonCourant : lignesLivraisons ) {
            if( ligneLivraisonCourant.getProduit().equals(p) ) {
                if( ligneLivraisonCourant.getQuantite() > 0 ) {
                    ligneLivraisonCourant.setQuantite( ligneLivraisonCourant.getQuantite() - 1 );
                }
            }
        }
        
        return this;
    }
    
}
