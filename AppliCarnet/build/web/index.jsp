<%-- 
    Document   : index
    Created on : 25 mars 2015, 18:43:22
    Author     : vivi
--%>

<%@page import="model.Utilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/MyCSS.css" rel="stylesheet" type="text/css"/>
        <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/myScript.js" type="text/javascript"></script>


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
                            <li class="active"><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                            <li><a href="htmlCalendar"><span class="glyphicon glyphicon-calendar"></span> Calendrier</a></li>
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
                            <li><% Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                                    if(utilisateur==null){%>
                                    <a href="SignUp.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a>
                            <%}else{%>
                                    <a href="SignUp.jsp"><span class="glyphicon glyphicon-user"></span> ${utilisateur.pseudo}</a>
                                    <%}%></li>
                            <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="row">
                <div class="jumbotron jumbotron-trans">

                    <h1>CarnetDeSport</h1>
                    <p class="lead">Bienvenue sur <strong>CarnetDeSport</strong>, envie de vous souvenir de toutes vos séance de sport, de toutes vos performances, de tout vos parcours, ...</p>

                </div>    
            </div>
            
            <footer class="footer">

                <div class="container">
                    <p class="text-muted">
                        ViviLog dernier mise a jour : 22/03/2015
                    </p>
                </div>

            </footer>

        </div>
    </body>
</html>
