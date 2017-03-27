package com.gustatif.dasi_project.util;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Classe de validation de données
 * @author Loic
 */
public class Validator {
    
    /**
     * Renvoie si une chaîne non vide et non nulle
     * @param s La chaîne a vérifier
     * @return boolean
     */
    public static boolean CheckNotNullAndNotEmpty( String s ) {
        if( s == null) {
            return false;
        }
        if( s.length() == 0 ) {
            return false;
        }
        return true;
    }
    
    /**
     * Renvoie si un collection est non vide et non nulle
     * @param c La collection à vérifier
     * @return boolean
     */
    public static boolean CheckNotNullAndNotEmpty( Collection c ) {
        if( null == c ) {
            return false;
        }
        return !c.isEmpty();
    }
    
    /**
     * Renvoie si une chaîne est un mail correct
     * @param s La chaîne à vérifier
     * @return boolean
     */
    public static boolean isMail( String s ) {
        
        if( s == null) {
            return false;
        }
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        return emailPattern.matcher(s).matches();
        
    }
    
}
