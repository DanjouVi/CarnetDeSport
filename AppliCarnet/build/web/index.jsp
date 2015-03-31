<%-- 
    Document   : index
    Created on : 25 mars 2015, 18:43:22
    Author     : vivi
--%>

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
        <div class="container"/>
             <%session.setAttribute("pageCourant","index");%>
            <jsp:include page="header.jsp"/>
            <div class="row">
                <div class="jumbotron jumbotron-trans">

                    <h1>CarnetDeSport</h1>
                    <p class="lead">Bienvenue sur <strong>CarnetDeSport</strong>, envie de vous souvenir de toutes vos séance de sport, de toutes vos performances, de tout vos parcours, ...</p>

                </div>    
            </div>
        </div>
        <jsp:include page="footer.html"/>
    </body>
</html>
