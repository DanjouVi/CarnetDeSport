/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.SeancesDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.sql.DataSource;

/**
 *
 * @author vivi
 */
public class Jour {
    

    public boolean estDansMois;
    private int numJour;
    private ArrayList<Seance> lesSeances;
    private SeancesDAO seancesDAO;

    public int getNumJour() {
        return numJour;
    }

    public Jour(GregorianCalendar jour,int numMois,Utilisateur utilisateur,DataSource dataSource) throws SQLException {
        numJour = jour.get(Calendar.DATE);
        estDansMois = (jour.get(Calendar.MONTH)+1==numMois);
        seancesDAO = new SeancesDAO(dataSource);
        lesSeances = seancesDAO.lesSeances(utilisateur, jour);
    }

    public ArrayList<Seance> getLesSeances() {
        return lesSeances;
    }
    
}
