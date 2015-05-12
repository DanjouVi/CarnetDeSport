<%-- 
    Document   : sports
    Created on : 7 avr. 2015, 19:44:29
    Author     : vivi
--%>

<%@page import="model.Sport"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/MyCSS.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="css/sports.css" rel="stylesheet" type="text/css"/>
        <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/sports.js" type="text/javascript"></script>
        <script src="js/bootstrap-table.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <%
                session.setAttribute("pageCourant", "sports");
                ArrayList<Sport> lesSports = (ArrayList<Sport>) request.getAttribute("lesSports");
                ArrayList<Sport> lesSportsDef = (ArrayList<Sport>) request.getAttribute("lesSportsDef");
            %>
            <jsp:include page="/header.jsp"/>

            <div class="row">
                <div class="col-md-12 col-md-offset-2">
                    <div class="jumbotron">
                        <h3>Sports</h3>
                        <HR size=5px>
                        <br/>
                        <h4>Mes Sports </h4>
                        <HR size=3px> 
                        <table data-toggle="table" data-cache="false" data-height="299" class="table-hover" data-row-style="rowStyle">
                            <thead>
                                <tr>
                                    <th>Nom</th>
                                    <th>Type</th>
                                    <th> Nb Seance associées</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int i = 0;
                                    for (i = 0; i < lesSports.size(); i++) {
                                        Sport sport = lesSports.get(i);
                                %>         
                                <tr >
                                    <td><%=sport.getNom()%></td>
                                    <td><%=sport.getTypeSeance()%></td>
                                    <td><%=sport.getNbSeance()%></td>
                                    <td><button value="<%=sport.getNom()%>" type="button" class="btn btn-primary" data-toggle="tooltip" data-placement="bottom" title="Modifier sport" onclick="modifSport(this.value,<%=sport.getNbSeance()%>)"><span class="glyphicon glyphicon-cog" ></span></button>&nbsp;<button value="<%=sport.getNom()%>" type="button" class="btn btn-danger" data-toggle="tooltip" data-placement="bottom" title="Suprimer sport" onclick="deleteSport(this.value,<%=sport.getNbSeance()%>)"><span class="glyphicon glyphicon-minus" ></span></button></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <h4>Nouveau Sport</h4>
                        <HR size=3px>
                        <div class="form-group">
                            <%String isPreDef = (String) session.getAttribute("isPreDef");
                            Sport newSport = (Sport) session.getAttribute("newSport");
                            %>
                            
                            <label control-label class="col-md-4"> Sport prédefini: </label>
                            <select <%if(newSport!= null){ %> disabled <%}%> name="SportPreDef" class="col-md-2" onchange="sportPreDef(this.value)">
                                <option value="true" selected> Oui </option>
                                <option value="false" <% if (isPreDef!=null && isPreDef.equals("false")){%> selected <%}%> > Non </option>
                            </select>
                            <div id="nomSportDef" <% if (isPreDef!=null && isPreDef.equals("false")){%>style="display: none"<%}%>>
                                <label control-label class="col-md-4"> Nom sport: </label>
                                <select <%if(newSport!= null){ %> disabled <%}%> name="nomSportDef" class="col-md-5">
                                    <option value="">choix de sport</option>
                                    <%
                                        for (i = 0; i < lesSportsDef.size(); i++) {
                                            Sport sport = lesSportsDef.get(i);
                                    %>
                                    <option <%if(newSport!=null && newSport.getNom().equals(sport.getNom())){%> selected <%}%> value="<%=sport.getNom()%>"> <%=sport.getNom()%> : <%=sport.getTypeSeance()%> </option>
                                    <% }%>
                                </select>
                            </div>
                            <div id="nomSport" <% if (isPreDef== null || isPreDef.equals("true")){%>style="display: none"<%}%>>
                                <label control-label class="col-md-4"> Nom sport: </label> 
                                <input <%if(newSport!= null){ %> disabled <%}%>type="text" name="nomSport"class="col-md-5" value="${newSport.nom}">
                            </div> 
                            <div class="row">
                                <button id="buttonEtapeSuivante" class="btn btn-primary col-md-4 col-md-offset-11" onclick="etapeSuivante()">Etape Suivante <span class="glyphicon glyphicon-chevron-right"></span></button> 
                            </div>

                        </div>

                        <div id="formSportBis" <%if(newSport== null) {%>style="display: none"<%}%>>
                            <div class='row'>
                                <div class="col-sm-3 col-sm-offset-1">
                                    <button type="button"  value="day" class="btn btn-block btn-calendar btn-true">
                                        <div class="row" id="dayButton">
                                            day<br/>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-14 col-sm-offset-1"><img  height="70" src="images/${newSport.urlImage}"></img>
                                            </div>
                                        </div>  
                                    </button>
                                </div>  
                                <form  class="col-sm-12" enctype="multipart/form-data" action="FileUpload" method="post">
                                    <label control-label class="col-md-5"> Image du Sport: </label><input  class="col-md-9" type="file" class="file file-input-new" name="file">
                                    <input type="submit" class="col-md-5" value="Upload File" />
                                </form>
                                <form class="col-sm-12" id="typeSport">
                                    <label control-label class="col-md-5"> Type de Sport: </label>
                                    <select class="col-md-9" id="typeSport" name="typeSport">
                                        <option value="default"> Default </option>
                                        <option value="distance" <%if(newSport!=null && newSport.getTypeSeance().equals("distance")){%> selected <%}%>> Distance </option>
                                        <option value="match"<%if(newSport!=null && newSport.getTypeSeance().equals("match")){%> selected <%}%>> Match </option>
                                    </select>

                                </form>
                            </div>  
                            <div class='row'>
                                <div class='col-xs-4 col-xs-offset-2'>
                                    <button id="valInscription" class="btn btn-danger btn-block" onclick="initSport()">Annuler&nbsp;<span class="glyphicon glyphicon-trash" aria-hidden="false" ></span></button>
                                </div>
                                <div class='col-xs-7 col-xs-offset-1 '>
                                    <button id="valInscription" class="btn btn-primary btn-block" onclick="saveSport()">Ajouter Sport&nbsp;<span class="glyphicon glyphicon-ok" aria-hidden="false" ></span></button>
                                </div>
                            </div>
                        </div>
                    </div>    
                </div>


            </div>   
    </body>
</html>
