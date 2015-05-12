/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers.Sports;

import DAO.SportsDAO;
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
import model.Sport;
import model.Utilisateur;

/**
 *
 * @author vivi
 */
@WebServlet(name = "SportExiste", urlPatterns = {"/SportExiste"})
public class SportExiste extends HttpServlet {
    
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
        response.setContentType("application/json");
        String nomSport = request.getParameter("nomSport");
         String isPreDef = request.getParameter("isPreDef");
         SportsDAO sportsDAO = new SportsDAO(dataSource);
         HttpSession session = request.getSession();
         Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
         
         try (PrintWriter out = response.getWriter();) {
            if(sportsDAO.sportExiste(utilisateur.getPseudo(),nomSport)){
                out.print("{\"existe\" : \"true\"}");
            }else{
                out.print("{\"existe\" : \"false\"}");
                if(isPreDef.equals("true")){
                    session.setAttribute("newSport",sportsDAO.leSport("admin", nomSport));
                }else{
                    session.setAttribute("newSport", new Sport(nomSport));
                }
                
                session.setAttribute("isPreDef", isPreDef);
                 
            }
            out.flush();
        }catch (SQLException ex) {
             log(ex.getMessage());
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
