/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers.Seances;

import DAO.SeancesDAO;
import DAO.SportsDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import model.Seance;
import model.Sport;
import model.Utilisateur;

/**
 *
 * @author vivi
 */
@WebServlet(name = "SeancesCtrler", urlPatterns = {"/SeancesCtrler"})
public class SeancesCtrler extends HttpServlet {

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
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        SportsDAO sportsDAO = new SportsDAO(dataSource);
        SeancesDAO seancesDAO = new SeancesDAO(dataSource);

        int numDay = Integer.parseInt(request.getParameter("numDay"));
        int numMois = Integer.parseInt(request.getParameter("numMois"));
        int annee = Integer.parseInt(request.getParameter("annee"));

        GregorianCalendar date = new GregorianCalendar();
        date.set(annee, numMois - 1, numDay);

        try {
            ArrayList<Sport> lesSports = sportsDAO.lesSports(utilisateur.getPseudo());
            request.setAttribute("lesSports", lesSports);

            ArrayList<String> lesLieus = seancesDAO.lesLieus(utilisateur);
            request.setAttribute("lesLieus", lesLieus);
        } catch (SQLException ex) {
            request.setAttribute("erreurMessage", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/pageErreur/erreurGen.jsp").forward(request, response);
        }
        ArrayList<Seance> lesSeances = new ArrayList();
        request.setAttribute("lesSeances", lesSeances);
        request.setAttribute("date", date);
        getServletContext().getRequestDispatcher("/WEB-INF/viewModalSeances.jsp").forward(request, response);

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
