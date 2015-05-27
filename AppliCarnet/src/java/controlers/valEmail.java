/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import DAO.ConnectionDAO;
import DAO.InscriptionDAO;
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
import model.Mail;
import model.Utilisateur;

/**
 *
 * @author vivi
 */
@WebServlet(name = "valEmail", urlPatterns = {"/valEmail"})
public class valEmail extends HttpServlet {

     //Connection a la BD
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

        String type = request.getParameter("type");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurTemp");

        if (type.equals("code")) {
            if (code.equals(utilisateur.getValEmail())) {
                session.setAttribute("utilisateur", utilisateur);
                ConnectionDAO connectionDAO = new ConnectionDAO(dataSource);
                try{
                   connectionDAO.valEmail(utilisateur.getPseudo());
                    request.getRequestDispatcher("index.jsp").forward(request, response);  
                }catch(SQLException ex){
                    request.setAttribute("erreurMessage", ex.getMessage());
                    request.getRequestDispatcher("WEB-INF/pageErreur/erreurInscription.jsp").forward(request, response);
                }              
            } else {
                request.setAttribute("erreurMessage", "Code d'activation non correcte");
                request.getRequestDispatcher("WEB-INF/pageErreur/erreurInscription.jsp").forward(request, response);
            }
        } else {
            Mail mail = new Mail(utilisateur.getEmail(), "Validation email CarnetDeSport", "Bonjour " + utilisateur.getPrenom() + ",\n"
                    + "\n Pour valider ton inscription a CarnetDeSport connect toi avec ton pseudo et ton mot de passe puis entre le code d'activation \n"
                    + "\n Pseudo : " + utilisateur.getPseudo()
                    + "\n Code d'activation : " + utilisateur.getValEmail());
            mail.sendMail();
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
