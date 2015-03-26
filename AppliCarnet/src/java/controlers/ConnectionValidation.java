/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import DAO.ConnectionDAO;
import DAO.InscriptionDAO;
import Exception.mailExistant;
import Exception.pseudoExistant;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import model.Utilisateur;

/**
 *
 * @author vivi
 */
@WebServlet(name = "ConnectionValidation", urlPatterns = {"/ConnectionValidation"})
public class ConnectionValidation extends HttpServlet {

    
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
       String pseudo = request.getParameter("pseudo");
       String password = request.getParameter("password");

       
       ConnectionDAO inscriptionDAO = new ConnectionDAO(dataSource);
       try{
           if(inscriptionDAO.identifiactionEstValide(pseudo, password)){
               Utilisateur utilisateur = new Utilisateur(pseudo, true);
               HttpSession session = request.getSession(true);
               session.setAttribute("utilisateur",utilisateur);
               request.getRequestDispatcher("index.jsp").forward(request, response);
           }else{
               request.setAttribute("erreurConnection", "IdentificationErreur");
               request.getRequestDispatcher("Login.jsp").forward(request, response);
           }
        }catch (SQLException ex) {
            request.setAttribute("erreurMessage", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/erreurInscription.jsp").forward(request, response);
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
