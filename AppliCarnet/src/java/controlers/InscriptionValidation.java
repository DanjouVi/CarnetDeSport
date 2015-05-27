/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import DAO.InscriptionDAO;
import Exception.mailExistant;
import Exception.pseudoExistant;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.UUID;
import model.Mail;

/**
 *
 * @author vivi
 */
@WebServlet(name = "InscriptionValidation", urlPatterns = {"/InscriptionValidation"})
public class InscriptionValidation extends HttpServlet {

    
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
       String email = request.getParameter("email");
       String nom = request.getParameter("nom");
       String prenom = request.getParameter("prenom");
       
       InscriptionDAO inscriptionDAO = new InscriptionDAO(dataSource);
       try{
           if(inscriptionDAO.mailExiste(email)){
               throw new mailExistant(email);
           }
           if(inscriptionDAO.pseudoExiste(pseudo)){
               throw new pseudoExistant(pseudo);
           }
           
        String codeVal = UUID.randomUUID().toString().split("-")[0];
        Mail mail = new Mail(email,"Validation email CarnetDeSport","Bonjour "+prenom +",\n" +
                "\n Pour valider ton inscription a CarnetDeSport connect toi avec ton pseudo et ton mot de passe puis entre le code d'activation \n"+
                "\n Pseudo : "+ pseudo+
                "\n Code d'activation : "+ codeVal);
        mail.sendMail();
        inscriptionDAO.addNewUtilisateur(pseudo, nom, prenom, email, password,codeVal);
       
        request.setAttribute("pseudo", pseudo);
        request.setAttribute("prenom", prenom);
        request.getRequestDispatcher("WEB-INF/ConfirmationInscription.jsp").forward(request, response);
        }catch (SQLException|mailExistant|pseudoExistant ex) {
            request.setAttribute("erreurMessage", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/pageErreur/erreurInscription.jsp").forward(request, response);
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
