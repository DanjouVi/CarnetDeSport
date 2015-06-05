/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author vivi
 */
public class Parcour {
    
   private int idParcours;
   private String nomParcours;
   private String description;
   private Double distance;
   private int denivele;    

    public Parcour(int idParcours, String nomParcours, String description, Double distance, int denivele) {
        this.idParcours = idParcours;
        this.nomParcours = nomParcours;
        this.description = description;
        this.distance = distance;
        this.denivele = denivele;
    }

    public Parcour(String nomParcours, String description, Double distance, int denivele) {
        this(0,nomParcours,description,distance,denivele);
    }

    public int getIdParcours() {
        return idParcours;
    }

    public String getNomParcours() {
        return nomParcours;
    }

    public String getDescription() {
        return description;
    }

    public Double getDistance() {
        return distance;
    }

    public int getDenivele() {
        return denivele;
    }

}

