/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Exception.convertionDureeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javax.sql.DataSource;
import model.Match;
import model.Parcour;
import model.Seance;
import model.SeanceDistance;
import model.SeanceMatch;
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

    public ArrayList<String> lesLieus(Utilisateur utilisateur) throws SQLException {
        Connection conn = null;
        ArrayList<String> lesLieus = new ArrayList();
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

    public void saveSeance(Seance seance, Utilisateur utilisateur, String sport) throws SQLException {

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            Statement Stmt = conn.createStatement();
            ResultSet rs = Stmt.executeQuery("Select max(idSeance)+1 as id from lesSeances");
            if (rs.next()) {
                seance.setIdSeance(rs.getInt("id"));
            }

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

            if (seance instanceof SeanceMatch) {
                SeanceMatch seanceMatch = (SeanceMatch) seance;
                ArrayList<Match> lesMatch = seanceMatch.getLesMatchs();
                for (Match match : lesMatch) {
                    rs = Stmt.executeQuery("Select max(idMatch)+1 as id from lesMatchs");
                    if (rs.next())
                        match.setIdMatch(rs.getInt("id"));

                    saveMatch(seanceMatch.getIdSeance(), match, conn);

                }
            }
            
            if(seance instanceof SeanceDistance) {
                SeanceDistance seanceDistance = (SeanceDistance) seance;
                if(seanceDistance.getaParcour()){
                    prepStmt = conn.prepareStatement("INSERT INTO lesSeancesDistances (idSeance,distance,denivele,idParcours) VALUES (?,?,?,?)");
                    prepStmt.setInt(4,seanceDistance.getParcour().getIdParcours());
                }else{
                   prepStmt = conn.prepareStatement("INSERT INTO lesSeancesDistances (idSeance,distance,denivele) VALUES (?,?,?)");
                }
                prepStmt.setInt(1,seanceDistance.getIdSeance());
                prepStmt.setDouble(2,seanceDistance.getDistance());
                prepStmt.setInt(3,seanceDistance.getDenivele());
                prepStmt.executeUpdate(); 
            }
            
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
            closeConnection(conn);
        }
    }

    public ArrayList<Seance> lesSeances(Utilisateur utilisateur, Calendar date) throws SQLException, convertionDureeException {
        Connection conn = null;
        ArrayList<Seance> lesSeances = new ArrayList();
        try {
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select idSeance from lesSeances where utilisateur = ? and jour = STR_TO_DATE(?,'%d/%m/%Y')");
            prepStmt.setString(1, utilisateur.getPseudo());
            String jour = date.get(Calendar.DATE) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR);
            prepStmt.setString(2, jour);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
               lesSeances.add(getSeance(rs.getInt("idSeance")));
            }
            return lesSeances;
        } finally {
            closeConnection(conn);
        }

    }
    
    
    public Seance getSeance(int idSeance)throws SQLException, convertionDureeException{
        Connection conn = null;
        Seance seance = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select nomSeance, lieu,DATE_FORMAT(jour,'%d/%m/%Y') as jour,description,duree,sport,meteo,typeSport,photo from lesSeances lSe join lesSports lSp on (sport = nom and lSp.utilisateur = lSe.utilisateur) where lSe.idSeance = ?");
            prepStmt.setInt(1, idSeance);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                Sport sport = new Sport(rs.getString("sport"), rs.getString("typeSport"), 0, rs.getString("photo"));
                if("default".equals(rs.getString("typeSport")))
                    seance = new Seance(idSeance, rs.getString("jour"), rs.getString("lieu"), rs.getString("description"), rs.getString("meteo"), rs.getString("nomSeance"), rs.getInt("duree"), sport);
                if("distance".equals(rs.getString("typeSport"))){
                    prepStmt = conn.prepareStatement("select idParcours,distance,denivele FROM lesSeancesDistances WHERE idSeance =?");
                    prepStmt.setInt(1, idSeance);
                    ResultSet rs2 = prepStmt.executeQuery();
                    rs2.next();
                    Parcour parcour =null;
                    int nbTours =0;
                    if(rs2.getInt("idParcours")!=0){
                        ParcoursDAO parcoursDAO = new ParcoursDAO(dataSource);
                        parcour =  parcoursDAO.getParcoursById(rs2.getInt("idParcours"));
                        nbTours = (int)(rs2.getInt("distance")/parcour.getDistance());
                    }
                        
                   seance = new SeanceDistance(idSeance, rs.getString("jour"), rs.getString("lieu"), rs.getString("description"), rs.getString("meteo"), rs.getString("nomSeance"), rs.getInt("duree"), sport, parcour,nbTours,rs2.getDouble("distance"),rs2.getInt("denivele"));
                } 
                if("match".equals(rs.getString("typeSport"))){
                     seance = new SeanceMatch(idSeance, rs.getString("jour"), rs.getString("lieu"), rs.getString("description"), rs.getString("meteo"), rs.getString("nomSeance"), rs.getInt("duree"), sport,getLesMatchs(idSeance, conn));
                }
            } 
        }finally{
             closeConnection(conn);
        } 
        return seance;
    }
    
    public void delSeance(Seance seance) throws SQLException{
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            if(seance instanceof SeanceMatch){
               SeanceMatch seanceMatch = (SeanceMatch) seance;
                for (Match match : seanceMatch.getLesMatchs()) {
                    PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM lesJoueurs where idMatch =?");
                    prepStmt.setInt(1, match.getIdMatch());
                    prepStmt.executeUpdate();
                }
                 PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM lesMatchs where idSeance =?");
                prepStmt.setInt(1, seanceMatch.getIdSeance());
                prepStmt.executeUpdate();
            } 
            if(seance instanceof SeanceDistance){
                PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM lesSeancesDistances where idSeance =?");
                prepStmt.setInt(1, seance.getIdSeance());
                prepStmt.executeUpdate(); 
            }
            
            PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM lesSeances where idSeance =?");
            prepStmt.setInt(1, seance.getIdSeance());
            prepStmt.executeUpdate(); 
            
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
            closeConnection(conn);
        }
    }

    private void saveMatch(int idSeance, Match match, Connection conn) throws SQLException {
        PreparedStatement prepStmtMatch = conn.prepareStatement("INSERT INTO lesMatchs (idMatch,idSeance,scoreJoueur,scoreAdv) VALUES (?,?,?,?)");
        PreparedStatement prepStmtJoueur = conn.prepareStatement("INSERT INTO lesJoueurs (nomJoueur,idMatch,estAdv) VALUES (?,?,?)");

        ArrayList<String> lesJoueurs = match.getLesJoueurs();
        ArrayList<String> lesAdversaires = match.getLesAdversaires();

        prepStmtMatch.setInt(1, match.getIdMatch());
        prepStmtMatch.setInt(2, idSeance);
        prepStmtMatch.setDouble(3, match.getScoreJoueurs());
        prepStmtMatch.setDouble(4, match.getScoreAdversaire());
        prepStmtMatch.execute();
        
        for(String joueur : lesJoueurs){
            prepStmtJoueur.setString(1, joueur);
            prepStmtJoueur.setInt(2, match.getIdMatch());
            prepStmtJoueur.setInt(3, 0);
             prepStmtJoueur.execute();
        }
        
        for(String adv : lesAdversaires){
            prepStmtJoueur.setString(1, adv);
            prepStmtJoueur.setInt(2, match.getIdMatch());
            prepStmtJoueur.setInt(3, 1);
             prepStmtJoueur.execute();
        }

    }
    
    private ArrayList<Match> getLesMatchs(int idSeance , Connection conn) throws SQLException{
        ArrayList<Match> lesMatchs = new ArrayList();
        PreparedStatement prepStmtMatch = conn.prepareStatement("select idMatch,scoreJoueur,scoreAdv from lesMatchs where idSeance=?");
        prepStmtMatch.setInt(1, idSeance);
        ResultSet rs = prepStmtMatch.executeQuery();
        while (rs.next()) {
           ArrayList<String> lesJoueurs = new ArrayList();
           ArrayList<String> lesAdv = new ArrayList();
           PreparedStatement prepStmtJoueur = conn.prepareStatement("select nomJoueur,estAdv from lesJoueurs where idMatch = ?");
           prepStmtJoueur.setInt(1, rs.getInt("idMatch"));
           ResultSet rs2 = prepStmtJoueur.executeQuery();
           while (rs2.next()) {
               String test = rs2.getString("nomJoueur");
              if(rs2.getInt("estAdv")==0){
                  lesJoueurs.add(rs2.getString("nomJoueur"));
              }else{
                  lesAdv.add(rs2.getString("nomJoueur"));
              }
           }
           lesMatchs.add(new Match(rs.getInt("idMatch"),rs.getInt("scoreJoueur"), rs.getInt("scoreAdv"), lesJoueurs, lesAdv));
        }
        return lesMatchs;
    }
    
}
