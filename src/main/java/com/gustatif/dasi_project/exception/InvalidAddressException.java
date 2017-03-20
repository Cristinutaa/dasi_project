package com.gustatif.dasi_project.exception;

/**
 * Cette exception peut être levée si l'adresse (postale) ne permet pas de calculer les
 * coordonnées GPS
 */
public class InvalidAddressException extends Exception {
    
    public InvalidAddressException( String msg) {
        super(msg);
    }
    
    public InvalidAddressException( String msg, Throwable cause ) {
        super(msg, cause);
    }
    
}
