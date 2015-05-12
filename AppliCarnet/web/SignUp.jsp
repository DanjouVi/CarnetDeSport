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
                        <h3>Inscription</h3>
                        <HR size=3px>
                        <br/>
                        <form id ="my-form" class="form-horizontal" method="post" action="InscriptionValidation"  onsubmit="return VerifInscription()">
                            <div class="form-group">
                                <label control-label class="col-md-5 col-lg-offset-1"> Pseudo : </label>
                                <input type="text" name="pseudo" class="col-md-7 col-lg-offset-1" value="Silf" required> </input> <span id="speudoUnique" class="glyphicon glyphicon-remove erreurForm" style="display: none"></span>
                            </div>
                            <div class="form-group">
                                <label control-label class="col-md-5 col-lg-offset-1"> Nom : </label>
                                <input type="text" name="nom" class="col-md-7 col-lg-offset-1" value="Danjou" required> </input>
                            </div>
                            <div class="form-group">
                                <label control-label class="col-md-5 col-lg-offset-1" > Prenom : </label>
                                <input type="text" name="prenom"  class="col-md-7 col-lg-offset-1" value="Vianney" required> </input>
                            </div>
                            <div class="form-group">
                                <label control-label class="col-md-5 col-lg-offset-1"> Mail : </label>
                                <input type="email" name="email" class="col-md-7 col-lg-offset-1" id="email" required value="monMail@mail"> </input>
                            </div>
                            <div class="form-group">
                                <label control-label class="col-md-5 col-lg-offset-1"> Mail : (Confimation) </label>
                                <input type="email" class="col-md-7 col-lg-offset-1 email"  id="emailVerif" required value="monMail@mail"> </input> <span id="emailDiff" class="glyphicon glyphicon-remove erreurForm" style="display: none"></span>
                            </div>
                            <div class="form-group">
                                <label control-label class="col-md-5 col-lg-offset-1"> Password : </label>
                                <input type="password" name="password" id="password" class="col-md-7 col-lg-offset-1" required value="pass"> </input>
                            </div>
                            <div class="form-group">
                                <label control-label class="col-md-5 col-lg-offset-1"> Password : (Confirmation) </label>
                                <input type="password" id="passwordVerif" class="col-md-7 col-lg-offset-1" required value="pass"> </input> <span id="passwordDiff" class="glyphicon glyphicon-remove erreurForm" style="display: none"></span>
                            </div>
                            <div class='row'>
                                <div class='col-xs-7 col-xs-offset-1'>
                                    <button type="reset" class="btn btn-danger btn-block">Effacer le contenu&nbsp;<span class="glyphicon glyphicon-trash"></span></button>
                                </div>
                                <div class='col-xs-7 col-xs-offset-1'>
                                    <button id="valInscription" class="btn btn-primary btn-block">Valider les informations&nbsp;<span class="glyphicon glyphicon-ok" aria-hidden="false" ></span></button>
                                </div>
                            </div>
                        </form>
                    </div>
                    
                </div>  
                 
            </div>
        </div>  
            <jsp:include page="footer.html"/>
    </body>
    
<script src="js/jsIncrpition.js" type="text/javascript"></script>
</html>
