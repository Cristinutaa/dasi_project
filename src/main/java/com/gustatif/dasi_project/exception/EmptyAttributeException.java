/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.exception;

/**
 *
 * @author Loic
 */
public class EmptyAttributeException extends Exception {
    
    public EmptyAttributeException( String msg ) {
        super(msg);
    }
    
    public EmptyAttributeException( String msg, Throwable cause) {
        super(msg, cause);
    }
    
}
