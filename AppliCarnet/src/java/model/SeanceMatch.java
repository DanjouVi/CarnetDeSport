/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Exception.convertionDureeException;
import java.util.ArrayList;

/**
 *
 * @author vivi
 */
public class SeanceMatch extends Seance{
    ArrayList<Match> lesMatchs;
    public SeanceMatch(int idSeance, String date, String lieu, String comment, String meteo, String nom, String duree) throws convertionDureeException {
        super(idSeance, date, lieu, comment, meteo, nom, duree);
        lesMatchs=new ArrayList<>();
    }
    
    public SeanceMatch(int idSeance, String date, String lieu, String comment, String meteo, String nom, int duree,Sport sport,ArrayList<Match> lesMatchs) throws convertionDureeException {
        super(idSeance, date, lieu, comment, meteo, nom, duree,sport);
        this.lesMatchs = lesMatchs;
    }
    
     public SeanceMatch( String date, String lieu, String comment, String meteo, String nom, String duree) throws convertionDureeException {
        super(0, date, lieu, comment, meteo, nom, duree);
        lesMatchs=new ArrayList<>();
    }

    public ArrayList<Match> getLesMatchs() {
        return lesMatchs;
    }

    public void setLesMatchs(ArrayList<Match> lesMatchs) {
        this.lesMatchs = lesMatchs;
    }
    
}
