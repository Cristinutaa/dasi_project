package com.gustatif.dasi_project.exception;

public class InvalidActionException extends Exception {
    
    public InvalidActionException( String msg ) {
        super( msg );
    }
    
    public InvalidActionException( String msg, Throwable cause ) {
        super( msg, cause );
    }
    
    
}
