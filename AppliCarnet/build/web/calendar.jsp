<%-- 
    Document   : index
    Created on : 19 févr. 2015, 11:27:07
    Author     : vivi
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="model.Mois"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/MyCSS.css" rel="stylesheet" type="text/css"/>
        <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/myScript.js" type="text/javascript"></script>
        <script src="js/MD5.js" type="text/javascript"></script>

        <title>JSP Page</title>
    </head>
    <body>
      <div class="container">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Carnet de sport</a>
                    </div>
                    <div>
                        <ul class="nav navbar-nav">
                            <li><a href="index.html"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                            <li class="active"><a href="htmlCalendar"><span class="glyphicon glyphicon-calendar"></span> Calendrier</a></li>
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-globe"></span> Parcours<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Afficher  <span class="glyphicon glyphicon-eye-open"></span></a></li>
                                    <li><a href="#">Nouveau  <span class="glyphicon glyphicon-plus"></span></a></li>
                                </ul>
                            </li>
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-stats"></span> Objectifs<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Afficher  <span class="glyphicon glyphicon-eye-open"></span></a></li>
                                    <li><a href="#">Nouveau  <span class="glyphicon glyphicon-plus"></span></a></li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="SignUp.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
             <div class="row">
                <div class="jumbotron jumbotron-trans">
                    <p class="lead">Le <strong>Calendrier</strong> résume toutes vos séances enregistrées. Cliquer sur un jour pour modifier ou ajouter une séance.</p>
                </div>    
            </div>
            <div id="Calendar"> 
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-1">
                        <button type="button" class="btn btn-primary btn-block btn-carre"  ><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span></button>
                    </div>
                    <div class="col-sm-4 ">
                        <button type="button" class="btn btn-primary btn-block disabled btn-carre"  >${mois.numMois}</button>
                    </div>
                    <div class="col-sm-2 ">
                        <button type="button" class="btn btn-primary btn-block btn-carre"  ><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></button>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-primary btn-block btn-carre"  ><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span></button>
                    </div>
                    <div class="col-sm-2 ">
                        <button type="button" class="btn btn-primary btn-block disabled btn-carre"  >${mois.annee}</button>
                    </div>
                    <div class="col-sm-2 ">
                        <button type="button" class="btn btn-primary btn-block btn-carre"  ><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-1">
                        <button type="button" class="btn  btn-primary btn-block disabled btn-carre"  >Lundi</button>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-primary btn-block disabled btn-carre"  >Mardi</button>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-primary btn-block disabled btn-carre"  >Mercredi</button>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class=" btn btn-primary btn-block disabled btn-carre"  >Jeudi</button>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-primary btn-block disabled btn-carre"  >Vendredi</button>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-primary btn-block disabled btn-carre"  >Samedi</button>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-primary btn-block disabled btn-carre"  >Dimanche</button>
                    </div>   
                </div>

                <%  int i, j;
                    Mois moisCourant = (Mois) request.getAttribute("mois");
                    int today = (Integer) request.getAttribute("today");
                    for (i = 0; i < 6; i++) {
                        
                        String classToday ="";
                        int jour = moisCourant.getJour(i, 0);
                        boolean estDansMois = moisCourant.getEstDansMois(i, 0);
                        if(today ==jour && estDansMois){
                            classToday="today";
                        }else{
                            classToday=""; 
                        }
                %>
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-1">
                        <button type="button" class="<%=classToday%> btn  btn-test btn-block btn-calendar btn-<%=estDansMois%>"  ><%=jour%></button>
                    </div>
                    <%
                        for (j = 1; j < 7; j++) {
                            jour = moisCourant.getJour(i, j);
                            estDansMois = moisCourant.getEstDansMois(i, j);
                            if(today ==jour && estDansMois){
                                classToday="today";
                            }else{
                                classToday=""; 
                            }
                    %>
                    <div class="col-sm-2">
                        <button type="button" class="<%=classToday%> btn  btn-test btn-block btn-calendar btn-<%=estDansMois%>"  ><%=jour%></button>
                    </div>
                    <%
                        }
                    %>
                </div><%
                        }
                    %>    
            </div>
       
        </div>
    </body>
</html>
