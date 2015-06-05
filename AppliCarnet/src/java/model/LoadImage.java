/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.net.URL;


/**
 *
 * @author vivi
 */
public class LoadImage {
    static String defaultUrl="images/url.png";
    
    static public String getUrl(String url){
        URL location = LoadImage.class.getProtectionDomain().getCodeSource().getLocation();
        String path = location.getFile();
        path = new File(path).getParent();

        File f = new File(path+"/../../../"+url);
        if(f.exists()){
           return url;  
        }else{
           return defaultUrl;
        }
           
    }
    
}
