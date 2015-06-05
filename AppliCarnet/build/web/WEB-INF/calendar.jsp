<%-- 
    Document   : index
    Created on : 19 févr. 2015, 11:27:07
    Author     : vivi
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="model.Mois"%>
<%@page import="model.Jour"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/MyCSS.css" rel="stylesheet" type="text/css"/>
        <link href="addons/jquery-ui-1.11.4.custom/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="addons/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css"/>
        <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jsCalendar.js" type="text/javascript"></script>
        <script src="js/MD5.js" type="text/javascript"></script>
        <script src="addons/jquery-ui-1.11.4.custom/jquery-ui.min.js" type="text/javascript"></script>
        <script src="addons/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <%session.setAttribute("pageCourant", "calendar");%>
            <jsp:include page="/header.jsp"/>
            <div class="row">
                <div class="jumbotron jumbotron-trans">
                    <p class="lead">Le <strong>Calendrier</strong> résume toutes vos séances enregistrées. Cliquer sur un jour pour modifier ou ajouter une séance.</p>
                </div>    
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <button type="button" class="btn btn-default" onclick="initCalendar()"><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span> retour a aujourd'huit</button>
                </div>
                <div class="col-sm-9">
                    <h2> Vos séances du mois : </h2>
                </div>
            </div>

            <div id="Calendar"> 
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-1">
                        <button type="button" class="btn btn-primary btn-block btn-carre" onclick="modifCalendar(${mois.numMois} - 1,${mois.annee})"  ><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span></button>
                    </div>
                    <div class="col-sm-4 ">
                        <button type="button" class="btn btn-primary btn-block disabled btn-carre "   >${mois.nomMois}</button>
                    </div>
                    <div class="col-sm-2 ">
                        <button type="button" class="btn btn-primary btn-block btn-carre" onclick="modifCalendar(${mois.numMois} + 1,${mois.annee})"  ><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></button>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-primary btn-block btn-carre" onclick="modifCalendar(${mois.numMois},${mois.annee} - 1)" ><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span></button>
                    </div>
                    <div class="col-sm-2 ">
                        <button type="button" class="btn btn-primary btn-block disabled btn-carre"  >${mois.annee}</button>
                    </div>
                    <div class="col-sm-2 ">
                        <button type="button" class="btn btn-primary btn-block btn-carre" onclick="modifCalendar(${mois.numMois},${mois.annee} + 1)" ><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></button>
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
                    Mois moisCourant = (Mois) session.getAttribute("mois");
                    int today = ((Calendar) request.getAttribute("today")).get(Calendar.DAY_OF_MONTH);
                    int month = ((Calendar) request.getAttribute("today")).get(Calendar.MONTH) + 1;
                    int year = ((Calendar) request.getAttribute("today")).get(Calendar.YEAR);
                    for (i = 0; i < 6; i++) {

                        String classToday = "";
                        int jour = moisCourant.getJour(i, 0);
                        Jour classJour = moisCourant.getClasseJour(i, 0);
                        boolean estDansMois = moisCourant.getEstDansMois(i, 0);
                        if (today == jour && estDansMois && year == moisCourant.getAnnee() && month == moisCourant.getNumMois()) {
                            classToday = "today";
                        } else {
                            classToday = "";
                        }
                %>
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-1">
                        <button type="button" class="<%=classToday%> btn  btn-test btn-block btn-calendar btn-<%=estDansMois%>"  <% if (estDansMois) {%> data-toggle="modal" data-target=".bs-example-modal-lg" onclick="dayClick(<%=jour%>,<%=month%>,<%=year%>)"<%}%>  >
                            <div class="row" id="dayButton">
                                <%=jour%><br/>
                            </div>
                            <div class="row">
                                <div class="col-sm-14 col-sm-offset-1"><% if (classJour.getLesSeances().size()>0){%><img  height="70" src="images/<%=classJour.getLesSeances().get(0).getSport().getUrlImage()%>"></img><%}%>
                                </div>
                            </div> 
                        </button>
                    </div>
                    <%
                        for (j = 1; j < 7; j++) {
                            jour = moisCourant.getJour(i, j);
                            classJour = moisCourant.getClasseJour(i, j);
                            estDansMois = moisCourant.getEstDansMois(i, j);
                            if (today == jour && estDansMois && year == moisCourant.getAnnee() && month == moisCourant.getNumMois()) {
                                classToday = "today";
                            } else {
                                classToday = "";
                            }
                    %>
                    <div class="col-sm-2">
                        <button type="button" class="<%=classToday%> btn  btn-test btn-block btn-calendar btn-<%=estDansMois%>"  <% if (estDansMois) {%> data-toggle="modal" data-target=".bs-example-modal-lg" onclick="dayClick(<%=jour%>,<%=month%>,<%=year%>)" <%}%>  >
                            <div class="row" id="dayButton">
                                <%=jour%><br/>
                            </div>
                            <div class="row">
                                <div class="col-sm-14 col-sm-offset-1"><% if (classJour.getLesSeances().size()>0){%><img  height="70" src="images/<%=classJour.getLesSeances().get(0).getSport().getUrlImage()%>"></img><%}%>
                                </div>
                            </div> 
                        </button>
                    </div>
                    <%
                        }
                    %>
                </div><%
                    }
                %>    
            </div>
            <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
                        </div>
                        ....
                    </div>
                </div>
            </div>
            <br/>
            <jsp:include page="/footer.html"/>
        </div>
    </body>
</html>

