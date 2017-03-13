package com.gustatif.dasi_project.metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class LigneLivraison extends Model {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    protected Livraison livraison = new Livraison();

    @ManyToOne
    protected Produit produit = new Produit();
    protected Integer quantite = 0;

    public LigneLivraison() {
    }

    public long getId() {
        return id;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
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
