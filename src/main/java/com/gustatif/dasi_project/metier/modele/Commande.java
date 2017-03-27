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

/**
 * Class rerpésentant une Commande Client à un Restaurant
 * @author Loic
 */
@Entity
public class Commande extends Model implements Serializable {
    
    /**
     * L'etat de la commande
     * en_cours : Le client est en train d'éditer sa Commande
     * vaidee : La Commande a été finalisée en Livraison. La commande n'est plus
     * annulable
     */
    public enum Etat {
        en_cours,
        validee
    }
    
    /**
     * Identifiant unique de la Commande
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /**
     * Le client auquel est rattaché la commande
     */
    @ManyToOne
    protected Client client;
    
    /**
     * Le restaurant auquel est rattaché la commande
     */
    @ManyToOne
    protected Restaurant restaurant;
    
    /**
     * L'état de la commande
     */
    protected Etat etat;
    
    /**
     * La livraison crée lors de la validation de la commande. Peut être null
     */
    @OneToOne
    protected Livraison livraison;
    
    /**
     * Les lignes commandes rattachées à la commande.
     */
    @OneToMany(mappedBy = "commande", cascade = {CascadeType.ALL})
    protected List<LigneCommande> lignesCommandes;
    
    /**
     * Constructeur par défaut
     */
    protected Commande() {}

    /**
     * Création d'une commande pour un Client et un Restaurant donnée
     * @param client Le Client effectuant la commande
     * @param restaurant Le Restaurant dans lequel est effectué la commande
     */
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
    
    /**
     * Renvoie la livraison correspondant à la validation de la Commande. Si
     * la commande est vide renvoie null
     * @return Livraison|null
     */
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
