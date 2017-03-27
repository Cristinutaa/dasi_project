package com.gustatif.dasi_project.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Classe correspondant à une ligne de commande
 * @author Loic
 */
@Entity
public class LigneCommande extends Model implements Serializable {
    
    /**
     * Renvoie l'identifiant unique de la ligneCommande
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Renvoie la commande à laquelle appartient la ligneCommande
     */
    @ManyToOne
    protected Commande commande = new Commande();

    /**
     * Le produit de la ligne
     */
    @ManyToOne
    protected Produit produit = new Produit();
    
    /**
     * La quantité de la ligne
     */
    protected Integer quantite = 0;

    /**
     * Constructeur par défaut
     */
    public LigneCommande() {
    }
    
    /**
     * Constructeur de ligne commande
     * @param c La commande à laquelle appartient la ligne
     * @param p Le produit commandé
     */
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

    @Override
    public String toString() {
        return "LigneCommande{" + "produit=" + produit.getDenomination() +" x " + quantite +'}';
    }
}
