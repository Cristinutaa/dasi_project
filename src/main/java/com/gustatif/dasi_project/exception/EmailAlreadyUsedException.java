package com.gustatif.dasi_project.exception;

/**
 * Cette exception est levée à la création d'un utilisateur si un utilisateur utilisant la même adresse a déjà
 * été enregistré dans la base de données.
 * @author rloic
 */
public class EmailAlreadyUsedException extends Exception {
    
    /**
     * Message par défaut de l'exception
     */
    protected static String msg = "L'adresse e-mail est déjà utilisée par un autre client";

    /**
     * Constructeur par défaut
     */
    public EmailAlreadyUsedException() {
        super(msg);
    }
    
    /**
     * Constructeur avec information de la cause
     * @param cause Cause
     */
    public EmailAlreadyUsedException( Throwable cause ) {
        super(msg, cause);
    }
    
}
