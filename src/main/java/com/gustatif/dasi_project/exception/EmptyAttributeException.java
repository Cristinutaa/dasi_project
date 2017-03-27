package com.gustatif.dasi_project.exception;

/**
 * Cette exception peut être levée lorsque l'attribut d'un objet n'a pas été correctement rempli (null ou "" par exemple)
 * @author Loic
 */
public class EmptyAttributeException extends Exception {
    
    /**
     * Constructeur avec spécification du message à afficher
     * @param msg Le message donnée par l'exception
     */
    public EmptyAttributeException( String msg ) {
        super(msg);
    }
    
    /**
     * Constructeur avec message et cause ayant produit l'exception
     * @param msg Le message à afficher
     * @param cause La cause de l'exception
     */
    public EmptyAttributeException( String msg, Throwable cause) {
        super(msg, cause);
    }
    
}
