package com.gustatif.dasi_project.exception;

/**
 * Cette exception peut être levée si l'adresse (postale) ne permet pas de calculer les
 * coordonnées GPS
 */
public class InvalidAddressException extends Exception {
    
    /**
     * Constructeur avec spécification du message à afficher
     * @param msg Le message donnée par l'exception
     */
    public InvalidAddressException( String msg) {
        super(msg);
    }
    
    /**
     * Constructeur avec message et cause ayant produit l'exception
     * @param msg Le message à afficher
     * @param cause La cause de l'exception
     */
    public InvalidAddressException( String msg, Throwable cause ) {
        super(msg, cause);
    }
    
}
