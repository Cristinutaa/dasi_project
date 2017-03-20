/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.exception;

/**
 * Une InvalidReferenceException peut être levée si l'utilisateur essaie de faire appel à des objets
 * qui n'existent pas dans la base de données.
 * @author Loic
 */
public class InvalidReferenceException extends Exception {
    
    public InvalidReferenceException( String msg ) {
        super( msg );
    }
    
    public InvalidReferenceException(String msg, Throwable cause) {
        super( msg, cause );
    }
    
}
