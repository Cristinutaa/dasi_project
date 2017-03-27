package com.gustatif.dasi_project.util;

import com.gustatif.dasi_project.config.Config;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Utilitaire de recherche d'image
 * @author Loic
 */
public class UrlPictureSearcher {
    
    /**
     * Renvoie l'url de l'image du restaurant. En cas d'erreur renvoie
     * Config.DEFAULT_RESTAURANT_PICTURE_URL
     * @param name Le nom du restaurant
     * @return String
     */
    public static String getUrlPictureForRestaurant( String name ) {
        
        try {
            name = name.replace(" ", "+");
            
            Document doc = Jsoup.connect("https://www.ecosia.org/images?q=restaurant+" + name).get();
            Elements wrapper = doc.getElementsByClass("image-result-wrapper");
            return wrapper.get(0).select("a > img").attr("src");
        } catch (IOException ex) {
            return Config.DEFAULT_RESTAURANT_PICTURE_URL;
        }
    }
    
}
