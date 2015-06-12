/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author vivi
 */
public class Match {
   private ArrayList<String> lesJoueurs;
   private  ArrayList<String> lesAdversaires;
   private double scoreJoueurs;
   private double scoreAdversaire;

    public Match(double scoreJoueurs, double scoreAdversaire) {
        lesJoueurs = new ArrayList();
        lesJoueurs.add("");
        lesAdversaires= new ArrayList();
        lesAdversaires.add("");
        this.scoreJoueurs = scoreJoueurs;
        this.scoreAdversaire = scoreAdversaire;
    }

    public ArrayList<String> getLesJoueurs() {
        return lesJoueurs;
    }

    public ArrayList<String> getLesAdversaires() {
        return lesAdversaires;
    }

    public double getScoreJoueurs() {
        return scoreJoueurs;
    }

    public double getScoreAdversaire() {
        return scoreAdversaire;
    }

    public void setScoreJoueurs(String scoreJoueurs) {
        try{
            this.scoreJoueurs =  Double.parseDouble(scoreJoueurs);
        }catch (Exception ex){
            this.scoreJoueurs = 0;
        }
        
    }

    public void setScoreAdversaire(String scoreAdversaire) {
        try{
            this.scoreAdversaire = Double.parseDouble(scoreAdversaire);
        }catch (Exception ex){
            this.scoreAdversaire = 0;
        }
    }
   

    
    
}
