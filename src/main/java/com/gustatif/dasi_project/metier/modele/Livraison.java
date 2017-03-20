package com.gustatif.dasi_project.metier.modele;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Livraison extends Model {
    
    public enum Etat {
        non_attribuee,
        en_cours,
        livree
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateDebut;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateFin;
    
    @OneToOne
    protected Commande commande;
    
    protected Etat etat;
    
    @ManyToOne
    protected Livreur livreur;

    public Livraison() {
    }

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
    
    public String toFormattedString() {
        
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy 'à' hh'h'mm");
        
        StringBuilder strB = new StringBuilder();
        
        strB.append("Détails de la livraison \n");
        strB.append(" * Date/Haure : " + format.format(dateDebut));
        if( livreur instanceof LivreurPersonne) {
            strB.append(" * Livreur : " + ((LivreurPersonne) livreur).getPrenom() + " " + ((LivreurPersonne) livreur).getNom() + "(n°"+ livreur.getId() +") \n"); 
        }
        strB.append(" * Client : \n");
        strB.append("    " + commande.client.getPrenom() + " " + commande.client.getNom() + "\n");
        strB.append("    " + commande.client.getAdresse() +"\n");
        strB.append(" Commande : \n");
        for( LigneCommande lc : commande.getLignesCommandes() ) {
            strB.append(" * " + lc.getQuantite() + lc.getProduit().getDenomination() + " : " + lc.getQuantite() + " x " + lc.getProduit().getPrix() + "€\n");
        }
        
        strB.append("TOTAL : " + commande.getPrixTotal() + "€");
        
        return strB.toString();
        
    }
    
}
