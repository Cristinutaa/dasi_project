package com.gustatif.dasi_project.exception;

public class InvalidAddressException extends Exception {
    
    public InvalidAddressException( String msg) {
        super(msg);
    }
    
    public InvalidAddressException( String msg, Throwable cause ) {
        super(msg, cause);
    }
    
}
