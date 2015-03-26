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
public class ConnectionDAO extends AbstractDataSourceDAO{

    public ConnectionDAO(DataSource dataSource) {
        super(dataSource);
    }
    
    public boolean identifiactionEstValide(String pseudo, String password) throws SQLException{
         Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM lesUtilisateurs where pseudo = ? and password =?");
            prepStmt.setString(1, pseudo);
            prepStmt.setString(2, password);
            ResultSet rs = prepStmt.executeQuery();
            return rs.next();
        }finally{
            closeConnection(conn);
        }
    }
    
}
