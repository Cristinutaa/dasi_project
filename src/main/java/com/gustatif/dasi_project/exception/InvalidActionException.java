package com.gustatif.dasi_project.exception;

/**
 * Cette exception peut est levée lorsqu'une action non autorisée a été demandée
 */
public class InvalidActionException extends Exception {
    
    public InvalidActionException( String msg ) {
        super( msg );
    }
    
    public InvalidActionException( String msg, Throwable cause ) {
        super( msg, cause );
    }
    
    
}
