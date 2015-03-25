package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class SignUp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/MyCSS.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"//code.jquery.com/jquery-2.1.3.min.js\"></script>\n");
      out.write("        <script src=\"bootstrap/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"js/myScript.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"js/MD5.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"js/jsIncrpition.js\" type=\"text/javascript\"></script>\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <nav class=\"navbar navbar-inverse\">\n");
      out.write("                <div class=\"container-fluid\">\n");
      out.write("                    <div class=\"navbar-header\">\n");
      out.write("                        <a class=\"navbar-brand\" id=\"CarnetDeSport\" href=\"#\">Carnet de sport</a>\n");
      out.write("                    </div>\n");
      out.write("                    <div>\n");
      out.write("                        <ul class=\"nav navbar-nav\">\n");
      out.write("                            <li class=\"active\"><a href=\"index.html\"><span class=\"glyphicon glyphicon-home\"></span> Home</a></li>\n");
      out.write("                            <li><a href=\"htmlCalendar\"><span class=\"glyphicon glyphicon-calendar\"></span> Calendrier</a></li>\n");
      out.write("                            <li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\"><span class=\"glyphicon glyphicon-globe\"></span> Parcours<span class=\"caret\"></span></a>\n");
      out.write("                                <ul class=\"dropdown-menu\">\n");
      out.write("                                    <li><a href=\"#\">Afficher  <span class=\"glyphicon glyphicon-eye-open\"></span></a></li>\n");
      out.write("                                    <li><a href=\"#\">Nouveau  <span class=\"glyphicon glyphicon-plus\"></span></a></li>\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\"><span class=\"glyphicon glyphicon-stats\"></span> Objectifs<span class=\"caret\"></span></a>\n");
      out.write("                                <ul class=\"dropdown-menu\">\n");
      out.write("                                    <li><a href=\"#\">Afficher  <span class=\"glyphicon glyphicon-eye-open\"></span></a></li>\n");
      out.write("                                    <li><a href=\"#\">Nouveau  <span class=\"glyphicon glyphicon-plus\"></span></a></li>\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                        </ul>\n");
      out.write("                        <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                            <li><a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> Sign Up</a></li>\n");
      out.write("                            <li><a href=\"#\"><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </nav>\n");
      out.write("\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-md-8 col-md-offset-4\">\n");
      out.write("                    <div class=\"jumbotron\">\n");
      out.write("                        <h3>Inscription</h3>\n");
      out.write("                        <HR size=3px>\n");
      out.write("                        <br/>\n");
      out.write("                        <form class=\"form-horizontal\">\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label control-label class=\"col-md-5 col-lg-offset-1\"> Pseudo : </label>\n");
      out.write("                                <input type=\"text\" id =\"pseudo\" class=\"col-md-7 col-lg-offset-1\" required> </input> <span id=\"speudoUnique\" class=\"glyphicon glyphicon-remove erreurForm\" style=\"display: none\"></span>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label control-label class=\"col-md-5 col-lg-offset-1\"> Nom : </label>\n");
      out.write("                                <input type=\"text\" id =\"nom\" class=\"col-md-7 col-lg-offset-1\" required> </input>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label control-label class=\"col-md-5 col-lg-offset-1\" > Prenom : </label>\n");
      out.write("                                <input type=\"text\" id=\"prenom\"  class=\"col-md-7 col-lg-offset-1\" required> </input>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label control-label class=\"col-md-5 col-lg-offset-1\"> Mail : </label>\n");
      out.write("                                <input type=\"email\" class=\"col-md-7 col-lg-offset-1\" id=\"email\" required> </input>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label control-label class=\"col-md-5 col-lg-offset-1\"> Mail : (Confimation) </label>\n");
      out.write("                                <input type=\"email\" class=\"col-md-7 col-lg-offset-1\"  id=\"emailVerif\" required> </input> <span id=\"emailDiff\" class=\"glyphicon glyphicon-remove erreurForm\" style=\"display: none\"></span>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label control-label class=\"col-md-5 col-lg-offset-1\"> Password : </label>\n");
      out.write("                                <input type=\"password\" id=\"passsword\" class=\"col-md-7 col-lg-offset-1\" required> </input>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label control-label class=\"col-md-5 col-lg-offset-1\"> Password : (Confirmation) </label>\n");
      out.write("                                <input type=\"password\" id=\"passwordVerif\" class=\"col-md-7 col-lg-offset-1\" required> </input> <span id=\"passDiff\" class=\"glyphicon glyphicon-remove erreurForm\" style=\"display: none\"></span>\n");
      out.write("                            </div>\n");
      out.write("                            <div class='row'>\n");
      out.write("                                <div class='col-xs-7 col-xs-offset-1'>\n");
      out.write("                                    <button type=\"reset\" class=\"btn btn-danger btn-block\">Effacer le contenu&nbsp;<span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></button>\n");
      out.write("                                </div>\n");
      out.write("                                <div class='col-xs-7 col-xs-offset-1'>\n");
      out.write("                                    <button id=\"valInscription\" class=\"btn btn-primary btn-block\">Valider les informations&nbsp;<span class=\"glyphicon glyphicon-ok\" ></span></button>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div> \n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("    \n");
      out.write("<script src=\"js/jsIncrpition.js\" type=\"text/javascript\"></script>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
