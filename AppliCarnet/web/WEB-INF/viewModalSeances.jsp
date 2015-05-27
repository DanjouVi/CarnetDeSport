<%-- 
    Document   : viewModal
    Created on : 2 avr. 2015, 18:59:48
    Author     : vivi
--%>

<%@page import="model.Sport"%>
<%@page import="model.Seance"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/jsModal.js" type="text/javascript"></script>

<%
    Calendar date = (Calendar) request.getAttribute("date");
    ArrayList<Seance> lesSeances = (ArrayList<Seance>) request.getAttribute("lesSeances");
    ArrayList<Sport> lesSports = (ArrayList<Sport>) request.getAttribute("lesSports");
    ArrayList<String> lesLieus = (ArrayList<String>) request.getAttribute("lesLieus");

    String[] nomMois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet",
        "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
    String strdate = "";

    if (date != null) {
        strdate = date.get(Calendar.DATE) + " " + nomMois[date.get(Calendar.MONTH)] + " " + date.get(Calendar.YEAR);
    }
%>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">&times;</span></button>
    <h3 class="modal-title" id="gridSystemModalLabel">Les Séance du : <%=strdate%></h3>
</div>

<div class="row col-sm-14 col-sm-offset-1">
    <%if (lesSeances.size() == 0) {%>
    <h4>Pas de séances enregistrée</h4>
    <%}%>
    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="panel panel-default">
            <div class="panel-heading"  data-toggle='collapse' href="#bodyNewSeance">
                <h4>&nbsp;&nbsp;Nouvelle séance</h4>
            </div>
            <div id="bodyNewSeance" class="panel-collapse collapse in">
                <div class="panel-body">
                    <div class="row">
                        <div class="form-group col-lg-14 col-lg-offset-1">
                            <label id="labelNomSeance">Nom de séances (*) : </label>
                            <input class="form-control" type="text" id="inputNomSeance" placeholder="Entrainnement trail ...."></input>
                        </div>
                        <div class="form-group col-lg-6 col-lg-offset-1">
                            <label>Durée de la séance : </label>
                            <input  class="form-control" type="text" id="dureeSeance" value="" />
                        </div>
                        <div class="form-group col-lg-5 col-lg-offset-1">
                            <label>Sport (*) : </label>
                            <select class="form-control" id="selectSport"onchange="chgSport(this.value)">
                                <% for (int i = 0; i < lesSports.size(); i++) {
                                        Sport leSport = lesSports.get(i);
                                %>
                                <option value="<%=leSport.getNom()%>_-_<%=leSport.getUrlImage()%>_-_<%=leSport.getTypeSeance()%>"><%=leSport.getNom()%></option>
                                <%}%> 
                            </select>
                        </div>
                        <div class="col-lg-2">
                            &nbsp;&nbsp;&nbsp;<img src="" height="64" id="imgSport" style="display: none"></img>
                        </div>
                        <div class="form-group col-lg-6 col-lg-offset-1">
                            <label >Lieu : </label>
                            <input class="form-control" list="listLieu" id="inputLieu">
                            <datalist id="listLieu">
                                <%
                                    for (int i = 0; i < lesLieus.size(); i++) {
                                %>
                                <option value="<%=lesLieus.get(i)%>">
                                    <%}%>
                            </datalist>
                        </div>
                        <div class="form-group col-lg-5 col-lg-offset-1">
                            <label>Meteo : </label>
                            <select class="form-control" id="selectMeteo" onchange="imageMeteo(this.value)">
                                <option value="">non renseigné</option>
                                <option value="soleil">Soleil</option>
                                <option value="variable">Variable</option>
                                <option value="nuage">Nuageux</option>
                                <option value="averses">Averses</option>
                                <option value="pluie">Pluie</option>
                                <option value="neige">Neige</option>
                                <option value="orage">Orage</option>
                                <option value="nuit">Nuit</option>  
                            </select>
                        </div>
                        <div class="col-lg-2">
                            &nbsp;&nbsp;&nbsp;<img src="" id="imgMeteo" style="display: none"></img>
                        </div>
                        <div class="form-group col-lg-14 col-lg-offset-1">
                            <label>Comment:</label>
                            <textarea class="form-control" rows="5" id="comment" placeholder="..."></textarea>
                        </div>
                        <div class="form-group col-lg-10 col-lg-offset-3">
                            <button class="btn btn-primary btn-block" onclick="valSeance('<%=date.get(Calendar.DATE)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR)%>')">Ajouter Seance <span class="glyphicon glyphicon-ok"></span></button>
                        </div>
                    </div>
                    <p>(*) champs obligatoire</p>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="row">
    coucou 
</div>


