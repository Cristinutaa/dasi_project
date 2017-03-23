package com.gustatif.dasi_project.vue;

import com.gustatif.dasi_project.dao.*;
import com.gustatif.dasi_project.metier.modele.*;
import com.gustatif.dasi_project.metier.service.ServiceTechnique;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Loic on 23/03/2017.
 */
public class Utility {

    public static void display(List<? extends Serializable> serializable) {

        if(serializable.isEmpty()) {
            System.out.println("[]");
        }

        for(Serializable s : serializable) {
            System.out.println(s.toString());
        }

    }

    public static void initData() {

        RestaurantDAO restaurantDAO = new RestaurantDAO();
        ClientDAO clientDAO = new ClientDAO();
        ProduitDAO produitDAO = new ProduitDAO();
        LivreurDAO livreurDAO = new LivreurDAO();
        CommandeDAO commandeDAO = new CommandeDAO();
        LivraisonDAO livraisonDAO = new LivraisonDAO();

        if( produitDAO.findAll().isEmpty() ) {
            JpaUtil.ouvrirTransaction();

            Produit biereNinkasi = new Produit();
            biereNinkasi.setDenomination("Biere");
            biereNinkasi.setDescription("Biere du ninkasi");
            biereNinkasi.setPoids(0.33);
            biereNinkasi.setPrix(2.50);
            produitDAO.insert(biereNinkasi);

            Produit hamburgerNinkasi = new Produit();
            hamburgerNinkasi.setDenomination("Hamburger");
            hamburgerNinkasi.setDescription("Hamburger du ninkasi");
            hamburgerNinkasi.setPoids(0.750);
            hamburgerNinkasi.setPrix(6.99);
            produitDAO.insert(hamburgerNinkasi);

            Produit vinTonnerreDeBrest = new Produit();
            vinTonnerreDeBrest.setDenomination("Vin");
            vinTonnerreDeBrest.setDescription("Vin du tonnerre de brest");
            vinTonnerreDeBrest.setPoids(0.75);
            vinTonnerreDeBrest.setPrix(7.85);
            produitDAO.insert(vinTonnerreDeBrest);


            Produit fishAndChipsTonnerreDeBrest = new Produit();
            fishAndChipsTonnerreDeBrest.setDenomination("Fish'Chips");
            fishAndChipsTonnerreDeBrest.setDescription("Fish'Chips du ninkasi");
            fishAndChipsTonnerreDeBrest.setPoids(1.0);
            fishAndChipsTonnerreDeBrest.setPrix(12.50);
            produitDAO.insert(fishAndChipsTonnerreDeBrest);

            Restaurant ninkasi = new Restaurant("Ninkasi Gratte ciel", "Blablabla", "6 Avenue Henri Barbusse, 69100 Villeurbanne");
            ninkasi.getProduits().add(biereNinkasi);
            ninkasi.getProduits().add(hamburgerNinkasi);
            ninkasi.setLatitudeLongitude(47.769195, 4.879224);
            restaurantDAO.insert(ninkasi);

            Restaurant tonnerreDeBrest = new Restaurant("Tonnerre de Brest Picot et Compagnie", "Bla", "249 Cours Emile Zola, 69100 Villeurbanne");
            tonnerreDeBrest.getProduits().add(vinTonnerreDeBrest);
            tonnerreDeBrest.getProduits().add(fishAndChipsTonnerreDeBrest);
            tonnerreDeBrest.setLatitudeLongitude(47.768009, 4.88168);
            restaurantDAO.insert(tonnerreDeBrest);

            LivreurPersonne livreurJean = new LivreurPersonne();
            livreurJean.setNom("JEAN");
            livreurJean.setPrenom("Louis");
            livreurJean.setAdresseBase("8 Rue Gervais Bussiere, 69100 Villeurbanne");
            livreurJean.setLibre(true);
            livreurJean.setChargeMaximale(20);
            ServiceTechnique.calculerCoordonnees(livreurJean);
            livreurDAO.insert(livreurJean);

            LivreurPersonne livreurMaurice = new LivreurPersonne();
            livreurMaurice.setNom("MAURICE");
            livreurMaurice.setPrenom("Louis");
            livreurMaurice.setAdresseBase("8 Rue Gervais Bussiere, 69100 Villeurbanne");
            livreurMaurice.setLibre(true);
            livreurMaurice.setChargeMaximale(20);
            ServiceTechnique.calculerCoordonnees(livreurMaurice);
            livreurDAO.insert(livreurMaurice);

            LivreurDrone livreurDrone = new LivreurDrone();
            livreurDrone.setChargeMaximale(1.0);
            livreurDrone.setLibre(true);
            livreurDrone.setVitesseMoyenne(20.0);
            livreurDrone.setAdresseBase("126 Rue Francis de Pressensé 69100 Villeurbanne");
            ServiceTechnique.calculerCoordonnees(livreurDrone);
            livreurDAO.insert(livreurDrone);

            JpaUtil.validerTransaction();
        }

    }
}
