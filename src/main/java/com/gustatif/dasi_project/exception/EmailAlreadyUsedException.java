/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.exception;

/**
 * Cette exception est levée à la création d'un utilisateur si un utilisateur utilisant la même adresse a déjà
 * été enregistré dans la base de données.
 * @author rloic
 */
public class EmailAlreadyUsedException extends Exception {
    
    protected static String msg = "L'adresse e-mail est déjà utilisée par un autre client";

    public EmailAlreadyUsedException() {
        super(msg);
    }
    
    public EmailAlreadyUsedException( Throwable cause ) {
        super(msg, cause);
    }
    
}
