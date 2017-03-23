package com.gustatif.dasi_project.metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Commande extends Model implements Serializable {
    
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
    
    @OneToMany(mappedBy = "commande", cascade = {CascadeType.ALL})
    protected List<LigneCommande> lignesCommandes;
    
    protected Commande() {}

    public Commande(Client client, Restaurant restaurant) {
        this.client = client;
        this.restaurant = restaurant;
        this.lignesCommandes = new ArrayList<>();
        this.etat = Etat.en_cours;
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
        if( this.lignesCommandes.isEmpty() ) {
            return null;
        }

        boolean containsProduct = false;
        for( LigneCommande l : lignesCommandes ) {
            if( l.getQuantite() > 0 ) {
                containsProduct = true;
                break;
            }
        }

        if(!containsProduct) {
            return null;
        }

        return new Livraison( this );
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    
    public double getPoids() {
        
        double poids = 0;
        
        for( LigneCommande ligneCommande : this.lignesCommandes ) {
            poids += (double) ligneCommande.quantite * ligneCommande.getProduit().getPoids();
        }
        return poids;
    }
    
    public double getPrixTotal() {
        
        double prixTotal = 0;
        
        for( LigneCommande ligneCommande : this.lignesCommandes ) {
            prixTotal += (double) ligneCommande.quantite * ligneCommande.getProduit().getPrix();
        }
        return prixTotal;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", client=" + client + ", restaurant=" + restaurant + ", etat=" + etat + ", livraison=" + livraison + ", lignesCommandes=" + lignesCommandes + '}';
    }
    
    
    
}
