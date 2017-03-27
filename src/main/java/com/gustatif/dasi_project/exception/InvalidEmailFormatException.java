package com.gustatif.dasi_project.exception;

/**
 * Cette exception peut être levée lors de la vérification d'une adresse mail
 * @author Loic
 */
public class InvalidEmailFormatException extends Exception {

    /**
     * Constructeur avec spécification du message à afficher
     * @param msg Le message donnée par l'exception
     */
    public InvalidEmailFormatException( String msg ) {
        super( msg );
    }

    /**
     * Constructeur avec message et cause ayant produit l'exception
     * @param msg Le message à afficher
     * @param cause La cause de l'exception
     */
    public InvalidEmailFormatException( String msg, Throwable cause ) {
        super( msg, cause );
    }

}
