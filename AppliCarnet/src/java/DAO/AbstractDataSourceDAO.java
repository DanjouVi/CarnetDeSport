/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;


/**
 *
 * @author vivi
 */
public abstract class AbstractDataSourceDAO {
    
    protected DataSource dataSource;

    protected AbstractDataSourceDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected void closeConnection(Connection conn) throws SQLException{
       if(conn!=null){
           conn.close();
       }
   }

}
