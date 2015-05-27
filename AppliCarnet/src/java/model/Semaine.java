/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.sql.DataSource;

/**
 *
 * @author vivi
 */
class Semaine {
    private Jour [] jours = new Jour [7];
    
    public Semaine (GregorianCalendar jour, int numMois,Utilisateur utilisateur,DataSource dataSource) throws SQLException{
        for (int i = 0; i < 7; i++) {
            jours[i] = new Jour(jour,numMois,utilisateur,dataSource); 
            jour.add(Calendar.DATE, 1);
        }
    }

    public Jour getJour(int i) {
         if (i <= 6 && i >= 0) {
            return jours[i];
         }
        return null;   
    }

}
