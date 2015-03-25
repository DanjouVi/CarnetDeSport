/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.GridBagConstraints;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;

/**
 *
 * @author vivi
 */
class Jour {

    public boolean estDansMois;
    private int numJour;

    public int getNumJour() {
        return numJour;
    }

    public Jour(GregorianCalendar jour,int numMois) {
        numJour = jour.get(Calendar.DATE);
        estDansMois = (jour.get(Calendar.MONTH)+1==numMois);
    }

}
