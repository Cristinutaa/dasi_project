package com.gustatif.dasi_project.metier.modele;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe correspondant à une livraison
 * @author Loic
 */
@Entity
public class Livraison extends Model implements Serializable {
    
    /**
     * Etat de la livraison
     */
    public enum Etat {
        non_attribuee,
        en_cours,
        livree
    }
    
    /**
     * Identifiant unique de la livraison
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * Date de début de la livraison (départ du livreur)
     */
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateDebut;
    
    /**
     * Date de fin de la livraison
     */
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateFin;
    
    /**
     * Commande correspondant à la livraison
     */
    @OneToOne
    protected Commande commande;
    
    /**
     * Etat de la livraison
     */
    protected Etat etat;
    
    /**
     * Livreur effectuant la livraison
     */
    @ManyToOne
    protected Livreur livreur;

    /**
     * Constructeur de la livraison
     */
    public Livraison() {
    }

    /**
     * Livraison crée à partir de la commande correspondante
     * @param commande La commande correspondante à la livraison
     */
    public Livraison(Commande commande) {
        this.commande = commande;
        this.dateDebut = Calendar.getInstance().getTime();
        this.dateFin = null;
        this.livreur = null;
        this.etat = Etat.non_attribuee;
    }

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

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", commande=" + commande +
                ", etat=" + etat +
                ", livreur=" + livreur +
                '}';
    }

}
