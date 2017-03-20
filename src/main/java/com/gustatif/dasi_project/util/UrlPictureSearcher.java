/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Loic
 */
public class UrlPictureSearcher {
    
    public static final String DEFAULT_RESTAURANT_PICTURE_URL = "";
    
    public static String getUrlPictureForRestaurant( String name ) {
        
        try {
            
            name = name.replace(" ", "+");
            
            Document doc = Jsoup.connect("https://www.ecosia.org/images?q=restaurant+" + name).get();
            Elements wrapper = doc.getElementsByClass("image-result-wrapper");
            return wrapper.get(0).select("a > img").attr("src");
        } catch (IOException ex) {
            return DEFAULT_RESTAURANT_PICTURE_URL;
        }
    }
    
}
