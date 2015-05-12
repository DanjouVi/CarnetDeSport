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
import model.Sport;
import model.Utilisateur;

/**
 *
 * @author vivi
 */
public class SportsDAO extends AbstractDataSourceDAO {

    public SportsDAO(DataSource dataSource) {
        super(dataSource);
    }
    
    public Sport leSport ( String utilisateur , String nomSport) throws SQLException{
           Connection conn = null;
           Sport leSport = null;
           try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select nom, typeSport,photo, nbSeance from viewLesSports where utilisateur = ? and nom = ?");
            prepStmt.setString(1, utilisateur);
            prepStmt.setString(2, nomSport);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                leSport = new Sport(rs.getString("nom"),rs.getString("typeSport"),0,rs.getString("photo"));
            }
            return leSport;
        }finally{
            closeConnection(conn);
        }
           
    }
    public ArrayList<Sport> lesSports(String utilisateur) throws SQLException{
        Connection conn = null;
        ArrayList<Sport> lesSports = new ArrayList();
        try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select nom, typeSport,photo, nbSeance from viewLesSports where utilisateur = ?");
            prepStmt.setString(1, utilisateur);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                lesSports.add(new Sport(rs.getString("nom"),rs.getString("typeSport"), rs.getInt("nbSeance"),rs.getString("photo")));
            }
            return lesSports;
        }finally{
            closeConnection(conn);
        }
        
    }
    
    public boolean sportExiste(String utilisateur,String nomSport) throws SQLException{
         Connection conn = null;
         try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select nom from viewLesSports where utilisateur = ? and nom = ?");
            prepStmt.setString(1, utilisateur);
            prepStmt.setString(2, nomSport);
            ResultSet rs = prepStmt.executeQuery();
            return rs.next();
        }finally{
            closeConnection(conn);
        }
    }
    
    public void saveSport(Sport sport ,Utilisateur utilisateur) throws SQLException{
         Connection conn = null;
         try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO lesSports (nom,typeSport,utilisateur,photo) VALUES (?,?,?,?)");
            prepStmt.setString(3, utilisateur.getPseudo());
            prepStmt.setString(2, sport.getTypeSeance());
            prepStmt.setString(1, sport.getNom());
            prepStmt.setString(4, sport.getUrlImage());
            prepStmt.executeUpdate();
        }finally{
            closeConnection(conn);
        }
    }
    
    public void delSport(String nomSport ,Utilisateur utilisateur) throws SQLException{
         Connection conn = null;
         try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM lesSports WHERE utilisateur = ? AND nom=?");
            prepStmt.setString(1, utilisateur.getPseudo());
            prepStmt.setString(2, nomSport);
            prepStmt.executeUpdate();
        }finally{
            closeConnection(conn);
        }
    }
    
      public void delSeances(String nomSport ,Utilisateur utilisateur) throws SQLException{
         Connection conn = null;
         try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("DELETE from lesSeances Where sport =? and utilisateur =?");
            prepStmt.setString(2, utilisateur.getPseudo());
            prepStmt.setString(1, nomSport);
            prepStmt.executeUpdate();
        }finally{
            closeConnection(conn);
        }
    }
}
