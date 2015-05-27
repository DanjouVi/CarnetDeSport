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


/**
 *
 * @author vivi
 */
public class InscriptionDAO  extends AbstractDataSourceDAO{

    public InscriptionDAO(DataSource dataSource) {
        super(dataSource);
    }
    
    public boolean pseudoExiste(String pseudo) throws SQLException{
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM lesUtilisateurs where pseudo = ?");
            prepStmt.setString(1, pseudo);
            ResultSet rs = prepStmt.executeQuery();
            return rs.next();
        }finally{
            closeConnection(conn);
        }
        
    }
    
    public boolean mailExiste(String mail) throws SQLException{
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM lesUtilisateurs where adresseMail = ?");
            prepStmt.setString(1, mail);
            ResultSet rs = prepStmt.executeQuery();
            return rs.next();
        }finally{
            closeConnection(conn);
        }
        
    }
    
    public void addNewUtilisateur(String pseudo,String nom,String prenom, String email, String password,String codeVal) throws SQLException{
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("insert into lesUtilisateurs values(?,?,?,?,?,?)");
            prepStmt.setString(1, pseudo);
            prepStmt.setString(2, nom);
            prepStmt.setString(3, prenom);
            prepStmt.setString(4, email);
            prepStmt.setString(5, password);
            prepStmt.setString(6, codeVal);
            prepStmt.executeUpdate();
        }finally{
            closeConnection(conn);
        }
        
    }
    
}
