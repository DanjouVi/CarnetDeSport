/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Exception.convertionDureeException;

/**
 *
 * @author vivi
 */
public class SeanceDistance extends Seance{
    private Parcour parcour;
    private int nbTour;
    private final Boolean aParcour;
    private double distance;
    private int denivele;
    public SeanceDistance(int idSeance, String date, String lieu, String comment, String meteo, String nom, String duree ,double distance,int denivele) throws convertionDureeException {
        super(idSeance, date, lieu, comment, meteo, nom, duree);
        aParcour = false;
        this.distance=distance;
        this.denivele= denivele;
    }

    public SeanceDistance(int idSeance,String date, String lieu, String comment, String meteo, String nom, String duree,Parcour parcour,int nbTour) throws convertionDureeException {
        super(idSeance,date, lieu, comment, meteo, nom, duree);
        aParcour=true;
        this.nbTour=nbTour;
        this.parcour=parcour;
        distance=parcour.getDistance()*nbTour;
        denivele=parcour.getDenivele()*nbTour;    
    }
    
    public SeanceDistance(int idSeance,String date, String lieu, String comment, String meteo, String nom, int duree,Sport sport,Parcour parcour,int nbTour,double distance,int denivele) throws convertionDureeException {
        super(idSeance,date, lieu, comment, meteo, nom, duree,sport);
        this.nbTour=nbTour;
        this.parcour=parcour;
        this.distance=distance;
        this.denivele= denivele;  
        aParcour= parcour!=null;
            
    }

    public Parcour getParcour() {
        return parcour;
    }

    public void setParcour(Parcour parcour) {
        this.parcour = parcour;
    }

    public int getNbTour() {
        return nbTour;
    }

    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getDenivele() {
        return denivele;
    }

    public void setDenivele(int denivele) {
        this.denivele = denivele;
    }

    public Boolean getaParcour() {
        return aParcour;
    }
    
    public double getVitesseMoy(){
        return distance/this.getDureeSec()*3600;
    }
    
    public double getPenteMoy(){
        return Math.atan(denivele/(distance*1000)); 
    }
    
    public double getVitesseAsc(){
        return ((double)denivele)/this.getDureeSec()*3600;
    }
    
    
}
