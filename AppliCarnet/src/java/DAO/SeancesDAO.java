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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javax.sql.DataSource;
import model.Seance;
import model.Sport;
import model.Utilisateur;

/**
 *
 * @author vivi
 */
public class SeancesDAO extends AbstractDataSourceDAO {

    public SeancesDAO(DataSource dataSource) {
        super(dataSource);
    }


    public ArrayList<String>  lesLieus(Utilisateur utilisateur) throws SQLException{
        Connection conn = null;
        ArrayList<String>  lesLieus = new ArrayList();
        try {
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select distinct lieu from lesSeances where utilisateur = ?");
            prepStmt.setString(1, utilisateur.getPseudo());
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
               lesLieus.add(rs.getString("lieu"));
            }
            return lesLieus;
        } finally {
            closeConnection(conn);
        }
        
    }
    
    public void saveSeance(Seance seance,Utilisateur utilisateur,String sport) throws SQLException{
         Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            Statement Stmt = conn.createStatement();
            ResultSet rs = Stmt.executeQuery("Select max(idSeance)+1 as id from lesSeances");
            if(rs.next())
                seance.setIdSeance(rs.getInt("id"));
            
            PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO lesSeances (idSeance,nomSeance,lieu,jour,description,meteo,sport,utilisateur,duree) VALUES (?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?)");
            prepStmt.setInt(1, seance.getIdSeance());
            prepStmt.setString(2, seance.getNom());
            prepStmt.setString(3, seance.getLieu());
            prepStmt.setString(4, seance.getDate());
            prepStmt.setString(5, seance.getComment());
            prepStmt.setString(6, seance.getMeteo());
            prepStmt.setString(7, sport);
            prepStmt.setString(8, utilisateur.getPseudo());
             prepStmt.setInt(9, seance.getDureeSec());
            
            prepStmt.executeUpdate();
            conn.commit();
        }catch(SQLException ex){
            conn.rollback();
            throw ex;
        }finally {
            conn.setAutoCommit(true);
            closeConnection(conn);
        }
    }
    
    public ArrayList<Seance> lesSeances(Utilisateur utilisateur,Calendar date) throws SQLException{
        Connection conn = null;
        ArrayList<Seance>  lesSeances = new ArrayList();
        try {
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select idSeance, nomSeance, lieu,DATE_FORMAT(jour,'%d/%m/%Y') as jour,description,duree,sport,meteo,typeSport,photo from lesSeances lSe join lesSports lSp on (sport = nom and lSp.utilisateur = lSe.utilisateur) where lSe.utilisateur = ? and jour = STR_TO_DATE(?,'%d/%m/%Y')");
            prepStmt.setString(1, utilisateur.getPseudo());
            String jour = date.get(Calendar.DATE)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR);
            prepStmt.setString(2, jour);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
              // lesSeances.add(rs.getString("lieu"));
                lesSeances.add(new Seance(rs.getInt("idSeance"), rs.getString("jour"), rs.getString("lieu"), rs.getString("description"), rs.getString("meteo"), rs.getString("nomSeance"),rs.getInt("duree"),new Sport(rs.getString("sport"), rs.getString("typeSport"), 0, rs.getString("photo"))));
            }
            return lesSeances;
        } finally {
            closeConnection(conn);
        }
        
    }
}
