package com.gustatif.dasi_project.exception;

/**
 * Une InvalidReferenceException peut être levée si l'utilisateur essaie de faire appel à des objets
 * qui n'existent pas dans la base de données.
 * @author Loic
 */
public class InvalidReferenceException extends Exception {
    
    /**
     * Constructeur avec spécification du message à afficher
     * @param msg Le message donnée par l'exception
     */
    public InvalidReferenceException( String msg ) {
        super( msg );
    }
    
    /**
     * Constructeur avec message et cause ayant produit l'exception
     * @param msg Le message à afficher
     * @param cause La cause de l'exception
     */
    public InvalidReferenceException(String msg, Throwable cause) {
        super( msg, cause );
    }
    
}
