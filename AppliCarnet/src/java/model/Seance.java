/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Exception.convertionDureeException;
import java.util.Calendar;

/**
 *
 * @author vivi
 */
public class Seance {
    private int idSeance;
    private String date;
    private String lieu;
    private String comment;
    private String meteo;  
    private String nom;
    private int duree;
    private Sport sport;
    

    public Seance(int idSeance, String date, String lieu, String comment, String meteo, String nom,String duree) throws convertionDureeException {
        this.idSeance = idSeance;
        this.date = date;
        this.lieu = lieu;
        this.comment = comment;
        this.meteo = meteo;
        this.nom = nom;
        this.duree = getDureeSecond(duree);
    }
    public Seance(int idSeance, String date, String lieu, String comment, String meteo, String nom,int duree,Sport sport){
        this.idSeance = idSeance;
        this.date = date;
        this.lieu = lieu;
        this.comment = comment;
        this.meteo = meteo;
        this.nom = nom;
        this.duree = duree;
        this.sport =sport;
    }
    public Seance(String date, String lieu, String comment, String meteo, String nom,String duree) throws convertionDureeException {
        this(1,date,lieu,comment,meteo,nom,duree);
    }

    public int getIdSeance() {
        return idSeance;
    }

    public String getDate() {
        return date;
    }

    public String getLieu() {
        return lieu;
    }

    public Sport getSport() {
        return sport;
    }

    public String getComment() {
        return comment;
    }

    public String getMeteo() {
        return meteo;
    }

    public String getNom() {
        return nom;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }
    
    private int getDureeSecond(String duree) throws convertionDureeException{
        if(duree==null||duree.equals(""))
             return 0;
        int dureeSec = 0;
        try{
           int hour =  Integer.parseInt(duree.split(":")[0]);
           int min =  Integer.parseInt(duree.split(":")[1]);
           dureeSec = hour*3600+min*60;
        }catch(Exception ex){
            throw new convertionDureeException(duree);
        }
        return dureeSec;
    }

    public int getDureeSec() {
        return duree;
    }
    
}
