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
            <%session.setAttribute("pageCourant", "login");%>
            <jsp:include page="header.jsp"/>
            <div class="row">
                <div class="col-md-8 col-md-offset-4">
                    <div class="jumbotron">
                        <h3>Inscription</h3>
                        <HR size=3px>
                        <br/>
                        <form id ="my-form" class="form-horizontal" method="post" action="ConnectionValidation"  onsubmit="return VerifConnection()">
                            <div class="form-group">
                                <label control-label class="col-md-5 col-lg-offset-1"> Pseudo : </label>
                                <input type="text" name="pseudo" class="col-md-7 col-lg-offset-1" value="Silf" required> </input> <span id="speudoUnique" class="glyphicon glyphicon-remove erreurForm" style="display: none"></span>
                            </div>
                            <div class="form-group">
                                <label control-label class="col-md-5 col-lg-offset-1"> Password : </label>
                                <input type="password" name="password" class="col-md-7 col-lg-offset-1" value="pass" required> </input>
                            </div>

                            <%if (request.getAttribute("erreurConnection") != null) {%>
                            <p class='erreurForm'>Pseudonime ou password incorrect</p>
                            <%}%>
                            <div class='row'>
                                <div class='col-xs-7 col-xs-offset-5'>
                                    <button id="valConnection" class="btn btn-primary btn-block">Connection&nbsp;<span class="glyphicon glyphicon-log-in" aria-hidden="false" ></span></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div> 
            </div>
        </div>
        <jsp:include page="footer.html"/>                     
    </body>

    <script src="js/jsConnection.js" type="text/javascript"></script>
</html>
