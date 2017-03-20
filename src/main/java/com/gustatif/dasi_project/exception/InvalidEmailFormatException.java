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
public class InvalidEmailFormatException extends Exception {

    public InvalidEmailFormatException( String msg ) {
        super( msg );
    }

    public InvalidEmailFormatException( String msg, Throwable cause ) {
        super( msg, cause );
    }

}
