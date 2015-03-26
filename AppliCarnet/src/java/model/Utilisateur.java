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

    public Utilisateur(String pseudo, boolean estConnecte) {
        this.pseudo = pseudo;
        this.estConnecte = estConnecte;
    }

    public String getPseudo() {
        return pseudo;
    }

    public boolean isConnecte() {
        return estConnecte;
    }
    
    
    
}
