package com.gustatif.dasi_project.util;

/**
 * Classe d'envoi factice de mail
 * @author Loic
 */
public class FakeMailer {
    
    /**
     * Caractère de fin de ligne
     */
    protected static final String END_LINE = "\n\r";
    
    /**
     * Fonction d'envoi de mail
     * @param expediteur L'expéditeur du mail
     * @param destinataire Le destinataire du mail
     * @param sujet Le sujet du mail
     * @param corps Le corps de message du mail
     */
    public static void sendMail( String expediteur, String destinataire, String sujet, String corps ) {
        
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append("Expéditeur: " + expediteur + END_LINE);
        strBuilder.append("Destinataire: " + destinataire + END_LINE);
        strBuilder.append("Sujet: " + sujet + END_LINE);
        strBuilder.append("Corps: " + corps + END_LINE);
        
        System.out.println(strBuilder.toString());
        
    }
    
}
