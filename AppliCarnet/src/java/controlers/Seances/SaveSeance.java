/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers.Seances;

import DAO.ParcoursDAO;
import DAO.SeancesDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import model.Match;
import model.Parcour;
import model.Seance;
import model.SeanceDistance;
import model.SeanceMatch;
import model.Utilisateur;

/**
 *
 * @author vivi
 */
@WebServlet(name = "SaveSeance", urlPatterns = {"/SaveSeance"})
public class SaveSeance extends HttpServlet {
    
    @Resource(name = "jdbc/BDCarnetDeSport")
    private DataSource dataSource;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Utilisateur utilisateur =(Utilisateur) session.getAttribute("utilisateur");
                
        SeancesDAO seancesDAO = new SeancesDAO(dataSource);
        try{
             Seance newSeance =null;
            if("match".equals(request.getParameter("type"))){
                SeanceMatch newSeanceMatch = new SeanceMatch(request.getParameter("date"),request.getParameter("lieu"),request.getParameter("comment"),request.getParameter("meteo"),request.getParameter("nomSeance"),request.getParameter("duree"));
                newSeanceMatch.setLesMatchs((ArrayList<Match>) session.getAttribute("lesMatchs"));
                newSeance = newSeanceMatch;
            }else if("distance".equals(request.getParameter("type"))){
                 if (request.getParameterMap().containsKey("distance"))
                    newSeance = new SeanceDistance(0, request.getParameter("date"), request.getParameter("lieu"),request.getParameter("comment"), request.getParameter("meteo"), request.getParameter("nomSeance"),request.getParameter("duree"), Double.parseDouble(request.getParameter("distance")), Integer.parseInt(request.getParameter("denivele")));
                
                 if (request.getParameterMap().containsKey("idParcours")){
                    ParcoursDAO parcoursDAO = new ParcoursDAO(dataSource);
                    Parcour parcour =  parcoursDAO.getParcoursById(Integer.parseInt(request.getParameter("idParcours")));
                    newSeance = new SeanceDistance(0, request.getParameter("date"), request.getParameter("lieu"),request.getParameter("comment"), request.getParameter("meteo"), request.getParameter("nomSeance"),request.getParameter("duree"), parcour, Integer.parseInt(request.getParameter("nbTours")));
                 }
            }else{
                newSeance = new Seance(request.getParameter("date"),request.getParameter("lieu"),request.getParameter("comment"),request.getParameter("meteo"),request.getParameter("nomSeance"),request.getParameter("duree"));
            }
            
            
            String sport = (String) request.getParameter("sport");
            seancesDAO.saveSeance(newSeance, utilisateur, sport);
            request.getRequestDispatcher("htmlCalendar").forward(request, response);
        }catch(Exception ex){
            request.setAttribute("erreurMessage", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/pageErreur/erreurGen.jsp").forward(request, response);
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
