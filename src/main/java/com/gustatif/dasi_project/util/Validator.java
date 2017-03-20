/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.util;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 *
 * @author Loic
 */
public class Validator {
    
    public static boolean CheckNotNullAndNotEmpty( String s ) {
        if( s == null) {
            return false;
        }
        if( s.length() == 0 ) {
            return false;
        }
        return true;
    }
    
    public static boolean CheckNotNullAndNotEmpty( Collection c ) {
        if( null == c ) {
            return false;
        }
        return !c.isEmpty();
    }
    
    public static boolean isMail( String s ) {
        
        if( s == null) {
            return false;
        }
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        return emailPattern.matcher(s).matches();
        
    }
    
}
