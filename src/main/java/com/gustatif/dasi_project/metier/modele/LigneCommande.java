package com.gustatif.dasi_project.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class LigneCommande extends Model implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    protected Commande commande = new Commande();

    @ManyToOne
    protected Produit produit = new Produit();
    protected Integer quantite = 0;

    public LigneCommande() {
    }
    
    public LigneCommande( Commande c, Produit p ) {
        
        this.commande = c;
        this.produit = p;
        this.quantite = 1;
        
    }

    public long getId() {
        return id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    
    
    
}
