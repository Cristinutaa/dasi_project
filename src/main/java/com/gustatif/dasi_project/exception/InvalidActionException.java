package com.gustatif.dasi_project.exception;

/**
 * Cette exception peut est levée lorsqu'une action non autorisée a été demandée
 * à un service
 */
public class InvalidActionException extends Exception {
    
    /**
     * Constructeur avec spécification du message à afficher
     * @param msg Le message donnée par l'exception
     */
    public InvalidActionException( String msg ) {
        super( msg );
    }
    
    /**
     * Constructeur avec message et cause ayant produit l'exception
     * @param msg Le message à afficher
     * @param cause La cause de l'exception
     */
    public InvalidActionException( String msg, Throwable cause ) {
        super( msg, cause );
    }
    
    
}
