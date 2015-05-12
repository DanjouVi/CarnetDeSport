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
import javax.sql.DataSource;
import model.Utilisateur;

/**
 *
 * @author vivi
 */
public class ConnectionDAO extends AbstractDataSourceDAO{

    public ConnectionDAO(DataSource dataSource) {
        super(dataSource);
    }
    
    public Utilisateur identifiactionEstValide(String pseudo, String password) throws SQLException{
         Connection conn = null;
         Utilisateur utilisateur =null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM lesUtilisateurs where pseudo = ? and password =?");
            prepStmt.setString(1, pseudo);
            prepStmt.setString(2, password);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                utilisateur = new Utilisateur(rs.getString("pseudo"),true,rs.getString("nom"),rs.getString("prenom"),rs.getString("adresseMail"));
            }
            return utilisateur;
        }finally{
            closeConnection(conn);
        }
    }
    
}
