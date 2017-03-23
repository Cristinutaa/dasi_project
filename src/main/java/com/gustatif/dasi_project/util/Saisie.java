package com.gustatif.dasi_project.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author DASI Team
 */
public class Saisie {

    public static String lireChaine(String invite) {
        String chaineLue = null;
        System.out.print(invite);
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            chaineLue = br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        return chaineLue;

    }

    public static Integer lireInteger(String invite) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite));
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
        }
        return valeurLue;
    }

    public static Integer lireInteger(String invite, Integer valMin, Integer valMax) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite + "[" + valMin + ";" + valMax + "] :"));
                if( valeurLue < valMin || valeurLue > valMax ) {
                    System.out.println("/!\\ La valeur doit être comprise entre " + valMin + " et " + valMax + "/!\\");
                    valeurLue = null;
                }
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
        }
        return valeurLue;
    }

    public static Integer lireInteger(String invite, List<Integer> valeursPossibles) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite));
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
            if (!(valeursPossibles.contains(valeurLue))) {
                System.out.println("/!\\ Erreur de saisie - Valeur non-autorisée /!\\");
                valeurLue = null;
            }
        }
        return valeurLue;
    }
    
    public static String lireChaine(String invite, List<String> valeursPossibles) {
        String valeurLue = null;
        while (valeurLue == null) {
            valeurLue = lireChaine(invite);
            if (!(valeursPossibles.contains(valeurLue))) {
                System.out.println("/!\\ Erreur de saisie - Valeur non-autorisée /!\\");
                valeurLue = null;
            }
        }
        return valeurLue;
    }

    public static Date lireDate( String invite) {

        System.out.println(invite);

        String dateFormat = "dd/MM/yyyy HH:mm";
        Scanner scanner = new Scanner(System.in);
        Date date = null;
        while( null == date) {
            try {
                date = new SimpleDateFormat(dateFormat).parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Format de date incorrect (format à suivre : 31/12/2000 24:00)");
            }
        }

        return date;
    }

    public static void pause() {
        lireChaine("--PAUSE--");
    }

    public static void main(String[] args) {
        
        System.out.println("Bonjour !");
        
        String nom = Saisie.lireChaine("Entrez votre nom: ");
        System.out.println("Bonjour, " + nom + " !");
        
        Integer age = Saisie.lireInteger("Entrez votre âge: ");
        System.out.println("Vous avez " + age + " ans.");
        
        Integer annee = Saisie.lireInteger("Entrez votre année au Département IF (3,4,5): ", Arrays.asList(3,4,5));
        System.out.println("Vous êtes en " + annee + "IF.");

        Saisie.pause();
        
        System.out.println("Au revoir !");
    }
}
