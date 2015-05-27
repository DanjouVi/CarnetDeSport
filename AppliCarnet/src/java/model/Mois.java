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
public class Mois {

    private int annee;

    public enum saison {
        ete,
        automne,
        hiver,
        printemp;
    }
    private int numMois;
    private String[] nomMois = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet",
        "Août", "Septembre", "Octobre", "Novembre", "Décembre" };
     
    public int getAnnee() {
        return annee;
    }
    public String getNomMois(){
        return nomMois[numMois-1];
    }
    public int getNumMois() {
        return numMois;
    }
    
    private Semaine[] semaines = new Semaine[6];

    public int getJour(int sem,int jour) {
        if (sem <= 6 && sem >= 0) {
            return semaines[sem].getJour(jour).getNumJour();
        }
        return -1;
    }
    public Jour getClasseJour(int sem,int jour){
         if (sem <= 6 && sem >= 0) {
            return semaines[sem].getJour(jour);
        }
        return null;
    }
     public boolean getEstDansMois(int sem,int jour) {
        if (sem <= 6 && sem >= 0) {
            return semaines[sem].getJour(jour).estDansMois;
        }
        return false;
    }

    public Mois(GregorianCalendar jour, Utilisateur utilisateur,DataSource dataSource) throws SQLException {
        numMois = jour.get(Calendar.MONTH) + 1;
        annee = jour.get(Calendar.YEAR);
        jour = getFirstWeekDay();
        for (int i = 0; i < 6; i++) {
            semaines[i] = new Semaine(jour, numMois,utilisateur,dataSource);
        }
    }

    private int getIndexOfToday(GregorianCalendar calendar) {

        int today = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        int indexOfToday = 0;

        switch (today) {
            case GregorianCalendar.MONDAY:
                indexOfToday = 1;
                break;
            case GregorianCalendar.TUESDAY:
                indexOfToday = 2;
                break;
            case GregorianCalendar.WEDNESDAY:
                indexOfToday = 3;
                break;
            case GregorianCalendar.THURSDAY:
                indexOfToday = 4;
                break;
            case GregorianCalendar.FRIDAY:
                indexOfToday = 5;
                break;
            case GregorianCalendar.SATURDAY:
                indexOfToday = 6;
                break;
            case GregorianCalendar.SUNDAY:
                indexOfToday = 7;
                break;

        }

        return indexOfToday;

    }

    private GregorianCalendar getFirstWeekDay() {
        GregorianCalendar day = new GregorianCalendar(annee, numMois - 1, 1);
        int numInWeek = getIndexOfToday(day);
        if (numInWeek > 1) {
            day.add(Calendar.DATE, -(numInWeek - 1));
        } else {
            day.add(Calendar.DATE, -7);
        }
        return day;
    }

}
