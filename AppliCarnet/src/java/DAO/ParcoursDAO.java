/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import model.Parcour;
import model.Sport;
import model.Utilisateur;

/**
 *
 * @author vivi
 */
public class ParcoursDAO extends AbstractDataSourceDAO {

    public ParcoursDAO(DataSource dataSource) {
        super(dataSource);
    }


    public ArrayList<Parcour> lesParcours(String utilisateur) throws SQLException {
        Connection conn = null;
        ArrayList<Parcour> lesParcours = new ArrayList();
        try {
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select idParcours,nomParcours,distance,denivele, description,traceGPX from lesParcours where utilisateur = ?");
            prepStmt.setString(1, utilisateur);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                lesParcours.add(new Parcour(rs.getInt("idParcours"), rs.getString("nomParcours"), rs.getString("description"), rs.getDouble("distance"), rs.getInt("denivele")));
            }
            return lesParcours;
        } finally {
            closeConnection(conn);
        }

    }
    
    public Parcour getParcoursById(int idParcours) throws SQLException {
         Connection conn = null;
         Parcour parcour = null;
          try {
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select idParcours,nomParcours,distance,denivele, description,traceGPX from lesParcours where idParcours = ?");
            prepStmt.setInt(1, idParcours);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                parcour = new Parcour(rs.getInt("idParcours"), rs.getString("nomParcours"), rs.getString("description"), rs.getDouble("distance"), rs.getInt("denivele"));
            }
            return parcour;
        } finally {
            closeConnection(conn);
        } 
    }

  
}
