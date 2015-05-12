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
public class Sport {

    private int nbSeance;
    private String nom;
    private String typeSeance;
    private ArrayList<Seance> lesSeances = new ArrayList();
    private String urlImage;

    //-------Constructeur----------
    public Sport(String nom, String typeSeance, int nbSeance, String urlImage) {
        this.nbSeance = nbSeance;
        this.nom = nom;
        this.typeSeance = typeSeance;
        this.urlImage = urlImage;
    }

    public Sport(String nom) {
        this(nom, "defauft", 0, null);
    }

    //-----Getter and Setter---------
    public String getUrlImage() {
        return urlImage;
    }

    public void setNbSeance(int nbSeance) {
        this.nbSeance = nbSeance;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTypeSeance(String typeSeance) {
        this.typeSeance = typeSeance;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getNbSeance() {
        return nbSeance;
    }

    public String getNom() {
        return nom;
    }

    public String getTypeSeance() {
        return typeSeance;
    }

}
