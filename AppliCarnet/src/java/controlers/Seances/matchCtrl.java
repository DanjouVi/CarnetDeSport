/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers.Seances;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Match;

/**
 *
 * @author vivi
 */
@WebServlet(name = "matchCtrl", urlPatterns = {"/matchCtrl"})
public class matchCtrl extends HttpServlet {

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
        //Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
       String action = request.getParameter("action");
       ArrayList<Match> lesMatchs=new ArrayList();
       
       if ("init".equals(action))
           lesMatchs.add(new Match(0, 0));
       
       if ("addMatch".equals(action)){
           lesMatchs = (ArrayList<Match>) session.getAttribute("lesMatchs");
           lesMatchs.add(new Match(0, 0));
       }
       
       if ("delMatch".equals(action)){
           int num = Integer.parseInt(request.getParameter("num"));
           lesMatchs = (ArrayList<Match>) session.getAttribute("lesMatchs");
           lesMatchs.remove(num);
       }
       
       if ("addPersonne".equals(action)){
           int num = Integer.parseInt(request.getParameter("num"));
           String type = request.getParameter("type");
           lesMatchs = (ArrayList<Match>) session.getAttribute("lesMatchs");
           if("Adv".equals(type)){
               lesMatchs.get(num).getLesAdversaires().add("");
           }else{
               lesMatchs.get(num).getLesJoueurs().add("");
           }
       }
       
       if ("delPersonne".equals(action)){
           int num = Integer.parseInt(request.getParameter("num"));
           int numPers = Integer.parseInt(request.getParameter("numPers"));
           String type = request.getParameter("type");
           lesMatchs = (ArrayList<Match>) session.getAttribute("lesMatchs");
           if("Adv".equals(type)){
               lesMatchs.get(num).getLesAdversaires().remove(numPers);
           }else{
               lesMatchs.get(num).getLesJoueurs().remove(numPers);
           }
       }
       
       if ("changePersonne".equals(action)){
           int num = Integer.parseInt(request.getParameter("num"));
           int numPers = Integer.parseInt(request.getParameter("numPers"));
           String type = request.getParameter("type");
           String value = request.getParameter("value");
           lesMatchs = (ArrayList<Match>) session.getAttribute("lesMatchs");
           if("Adv".equals(type)){
               lesMatchs.get(num).getLesAdversaires().set(numPers, value);
           }else{
               lesMatchs.get(num).getLesJoueurs().set(numPers, value);
           }
       }
       
        if ("changeScore".equals(action)){
           int num = Integer.parseInt(request.getParameter("num"));
           String type = request.getParameter("type");
           String value = request.getParameter("value");
           lesMatchs = (ArrayList<Match>) session.getAttribute("lesMatchs");
           if("Adv".equals(type)){
               lesMatchs.get(num).setScoreAdversaire(value);
           }else{
               lesMatchs.get(num).setScoreJoueurs(value);
           }
       }
        
        if ("verifMatch".equals(action)){
           lesMatchs = (ArrayList<Match>) session.getAttribute("lesMatchs");
           response.setContentType("application/json");
           
            try (PrintWriter out = response.getWriter();) {
                for(int numMatch=0 ;numMatch<lesMatchs.size();numMatch++){
                    Match match = lesMatchs.get(numMatch);
                    for(int numAdv =0 ; numAdv < match.getLesAdversaires().size();numAdv++){
                        
                    }
                }
                String json = new Gson().toJson(lesMatchs);
                out.print("{\"test\" :"+json+"}");
                out.flush();
         }  
            
       }
       
       
       session.setAttribute("lesMatchs", lesMatchs);   
       getServletContext().getRequestDispatcher("/WEB-INF/viewMatch.jsp").forward(request, response);
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
