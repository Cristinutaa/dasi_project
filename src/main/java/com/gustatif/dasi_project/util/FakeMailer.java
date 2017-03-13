package com.gustatif.dasi_project.util;

public class FakeMailer {
    
    protected static final String END_LINE = "\n\r";
    
    public static void sendMail( String expediteur, String destinataire, String sujet, String corps ) {
        
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append("Expéditeur: " + expediteur + END_LINE);
        strBuilder.append("Destinataire: " + destinataire + END_LINE);
        strBuilder.append("Sujet: " + sujet + END_LINE);
        strBuilder.append("Corps: " + END_LINE);
        
        System.out.println(strBuilder.toString());
        
    }
    
}
