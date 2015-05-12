<%-- 
    Document   : SignIn
    Created on : 24 mars 2015, 12:50:51
    Author     : vivi
--%>

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
        <script src="js/jsIncrpition.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <%session.setAttribute("pageCourant","signUp");%>
            <jsp:include page="header.jsp"/>
            <div class="row">
                <div class="col-md-8 col-md-offset-4">
                    <div class="jumbotron">
                        <h3>Votre Compte</h3>
                        <HR size=5px>
                        <br/>
                        <h4>Vos Informations</h4>
                        <HR size=3px> 
                        <div class="row"><label control-label class="col-md-5 "> Pseudo : </label><span class="col-md-5 ">${utilisateur.pseudo}</span> </div>
                        <div class="row"><label control-label class="col-md-5 "> Nom : </label><span class="col-md-5 ">${utilisateur.nom}</span> </div>
                        <div class="row"><label control-label class="col-md-5 "> Prenom : </label><span class="col-md-5 ">${utilisateur.prenom}</span> </div>
                        <div class="row"><label control-label class="col-md-5 "> Email : </label><span class="col-md-5 ">${utilisateur.email}</span> </div>
                    </div>
                </div>    
            </div>
        </div>   
        <jsp:include page="footer.html"/>
    </body>
    
<script src="js/jsIncrpition.js" type="text/javascript"></script>
</html>
