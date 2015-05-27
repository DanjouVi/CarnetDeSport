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
public class Utilisateur {
    private String pseudo;
    private boolean estConnecte;
    private String nom;
    private String prenom;
    private String email;
    private String valEmail;



    public Utilisateur(String pseudo, boolean estConnecte, String nom, String prenom, String email,String valEmail) {
        this.pseudo = pseudo;
        this.estConnecte = estConnecte;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.valEmail=valEmail;
    }

    public String getValEmail() {
        return valEmail;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }
    
    public boolean isConnecte() {
        return estConnecte;
    }
    
    
    
}
