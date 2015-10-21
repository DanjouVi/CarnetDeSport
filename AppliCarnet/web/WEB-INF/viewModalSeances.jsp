<%-- 
    Document   : viewModal
    Created on : 2 avr. 2015, 18:59:48
    Author     : vivi
--%>

<%@page import="model.Match"%>
<%@page import="model.SeanceMatch"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.SeanceDistance"%>
<%@page import="model.Parcour"%>
<%@page import="model.LoadImage"%>
<%@page import="model.Sport"%>
<%@page import="model.Seance"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/jsModal.js" type="text/javascript"></script>
<link href="css/modal.css" rel="stylesheet" type="text/css"/>
<%

    Calendar date = (Calendar) request.getAttribute("date");
    
    //String modifSeances = (String) request.getAttribute("modifSeance");
    
  
    ArrayList<Seance> lesSeances = (ArrayList<Seance>) request.getAttribute("lesSeances");
    ArrayList<Sport> lesSports = (ArrayList<Sport>) request.getAttribute("lesSports");
    ArrayList<String> lesLieus = (ArrayList<String>) request.getAttribute("lesLieus");
    ArrayList<Parcour> lesParcours = (ArrayList<Parcour>) request.getAttribute("lesParcours");
      
    String modifSeanceStr = (String) request.getAttribute("modifSeance");
   boolean modifSeance = modifSeanceStr.equals("true");
   Seance seanceModif = (Seance) request.getAttribute("seanceModif");
   int size =0;
   if(!modifSeance){
       size = lesSeances.size();
   }
     
    String[] nomMois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet",
        "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
    String strdate = "";

    if (date != null) {
        strdate = date.get(Calendar.DATE) + " " + nomMois[date.get(Calendar.MONTH)] + " " + date.get(Calendar.YEAR);
    }
    
    NumberFormat formatter = new DecimalFormat("#0.00"); 
%>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">&times;</span></button>
    <h3 class="modal-title" id="gridSystemModalLabel">Les Séance du : <%=strdate%></h3>
</div>

<div class="row col-sm-14 col-sm-offset-1">
    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <%
        if(!modifSeance){
            for (int i = 0; i < lesSeances.size(); i++) {
                Seance seance = lesSeances.get(i);
        %>
        <div class="panel panel-default">
            <div class="panel-heading text-center"  data-toggle='collapse' href="#bodySeance<%=i%>">
                <img align="left" height="50" src="<%=LoadImage.getUrl("images/" + seance.getSport().getUrlImage())%>"></img>
                <font size="6"> </font>
                <font size="4"><%=seance.getNom()%></font>
                <img align="right" height="50" src="<%=LoadImage.getUrl("images/meteo/" + seance.getMeteo() + ".png")%>"></img>
            </div>
            <div id="bodySeance<%=i%>" class="panel-collapse collapse">
                <div class="panel-body">
                    <div class="form-group col-lg-6 col-lg-offset-1">
                        <label >Sport : </label>
                        <%=seance.getSport().getNom()%>
                    </div>
                    <div class="form-group col-lg-6 col-lg-offset-1">
                        <label >lieu : </label>
                        <%=seance.getLieu()%>
                    </div>
                    <div class="form-group col-lg-6 col-lg-offset-1">
                        <label >Durée : </label>
                        <%=seance.getDureeAff()%>
                    </div>
                    <div class="form-group col-lg-6 col-lg-offset-1">
                        <label >Meteo  : </label>
                        <%=seance.getMeteo()%>
                    </div>
                    <% if(seance instanceof SeanceDistance){
                        SeanceDistance seanceDistance =(SeanceDistance)seance;
                        %>
                        <div class="form-group col-lg-6 col-lg-offset-1">
                            <label >Distance (km): </label>
                            <span class="badge"><%=seanceDistance.getDistance()%></span>
                        </div>
                        <div class="form-group col-lg-6 col-lg-offset-1">
                            <label >Dénivele (m): </label>
                             <span class="badge"><%=seanceDistance.getDenivele()%></span>
                        </div>
                        <div class="form-group col-lg-6 col-lg-offset-1">
                            <label >Vitesse moyenne (km/h): </label>
                             <span class="badge"><%=formatter.format(seanceDistance.getVitesseMoy())%></span>
                        </div>
                        <div class="form-group col-lg-6 col-lg-offset-1">
                            <label >Vitesse Ascensionnel (m/h): </label>
                             <span class="badge"><%=formatter.format(seanceDistance.getVitesseAsc())%></span>
                        </div>
                    <%}
                    if(seance instanceof SeanceMatch){
                        SeanceMatch  seanceMatch =(SeanceMatch)seance;
                        ArrayList<Match> lesMatchs = seanceMatch.getLesMatchs();
                        for(int j=0;j<lesMatchs.size();j++){
                            Match match = lesMatchs.get(j);
                        %>
                            <div class="row">
                                 <div class="jumbotron match col-lg-14 col-lg-offset-1">
                                     <div class="col-lg-5">
                                         <div style ="border :solid 2px <%if(match.gagnant().equals("adversaires")){%> red<%}else{%> blue<%}%>;">
                                         <%for(int numJoueur =0;numJoueur<match.getLesJoueurs().size();numJoueur++){%>
                                            <%=match.getLesJoueurs().get(numJoueur)%><br/>
                                         <%}%>
                                         </div>
                                     </div>
                                     <div class="col-lg-5">
                                         <h4 class="text-center">VS</h4>
                                         <%if(match.gagnant().equals("adversaires")){%>
                                                <span class="label label-warning asBadge"><%=(int) match.getScoreJoueurs()%></span>&nbsp;&nbsp;&nbsp;à&nbsp;&nbsp;&nbsp;<span class="label label-success asBadge"><%=(int) match.getScoreAdversaire()%></span>
                                         <%}else if(match.gagnant().equals("joueurs")){%>
                                                <span class="label label-success asBadge"><%=(int) match.getScoreJoueurs()%></span>&nbsp;&nbsp;&nbsp;à&nbsp;&nbsp;&nbsp;<span class="label label-warning asBadge"><%=(int) match.getScoreAdversaire()%></span>
                                         <%}else{%>
                                                <span class="label label-primary asBadge"><%=(int) match.getScoreJoueurs()%></span>&nbsp;&nbsp;&nbsp;à&nbsp;&nbsp;&nbsp;<span class="label label-primary asBadge"><%=(int) match.getScoreAdversaire()%></span>
                                         <%}%>
                                     </div>
                                     <div class="col-lg-5">
                                         <div style ="border :solid 2px <%if(match.gagnant().equals("joueurs")){%> red<%}else{%> blue<%}%>;">
                                          <%for(int numAdv =0;numAdv<match.getLesAdversaires().size();numAdv++){%>
                                            <%=match.getLesAdversaires().get(numAdv)%><br/>
                                         <%}%>
                                         </div>
                                     </div>
                                 </div>
                            </div>    
                            
                    <%    }
                    }
                    %>
                    <div class="form-group col-lg-14 col-lg-offset-1">
                        <label>Comment:</label>
                        <p><%=seance.getComment()%></p>
                    </div>    
                </div>  
              <div class="panel-footer">
                  <input class="btn btn-warning btn-xs" type="button" onclick="delSeance(<%=seance.getIdSeance()%>)" value="Suprimer"></input>
                  <input class="btn btn-primary btn-xs" type="button" onclick="modifSeance(<%=seance.getIdSeance()%>,<%=date.get(Calendar.DATE)%>,<%=date.get(Calendar.MONTH)%>,<%=date.get(Calendar.YEAR)%>)" value="Modifier">
              </div>
            </div>
        </div>
        <%}
       }
        %>

        <div class="panel panel-default">
            <div class="panel-heading"  data-toggle='collapse' href="#bodyNewSeance">
                <% if(modifSeance){%>
                        <h4>&nbsp;&nbsp;Modifier séance : <%=seanceModif.getNom()%></h4>
                  <%}else{%>
                        <h4>&nbsp;&nbsp;Nouvelle séance</h4>
                 <%}%>
            </div>
            <div id="bodyNewSeance" class="panel-collapse collapse <%if (size <= 0) {%>in<%}%>">
                <div class="panel-body">
                    <div class="row">
                        <div class="form-group col-lg-14 col-lg-offset-1">
                            <label id="labelNomSeance">Nom de séances (*) : </label>
                            <input class="form-control" type="text" id="inputNomSeance" placeholder="Entrainnement trail ...." <% if(modifSeance){%>value="<%=seanceModif.getNom()%>"<%}%></input>
                        </div>
                        <div class="form-group col-lg-6 col-lg-offset-1">
                            <label>Durée de la séance : </label>
                            <input  class="form-control" type="text" id="dureeSeance" value="<% if(modifSeance){%><%=seanceModif.getDuree()%><%}%>" />
                        </div>
                        <div class="form-group col-lg-5 col-lg-offset-1">
                            <label>Sport (*) : </label>
                            <select class="form-control" id="selectSport"onchange="chgSport(this.value)">
                                <% for (int i = 0; i < lesSports.size(); i++) {
                                        Sport leSport = lesSports.get(i);
                                %>
                                <option value="<%=leSport.getNom()%>_-_<%=leSport.getUrlImage()%>_-_<%=leSport.getTypeSeance()%>" <% if(modifSeance && seanceModif.getSport().getNom().equals(leSport.getNom())){%>selected<%}%>><%=leSport.getNom()%></option>
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
                        <div id="typeDistance" style="display :none">
                            <div class="form-group col-lg-3 col-lg-offset-1">
                                <label>Parcour : </label>
                                <select class="form-control" id="selectParcours" onchange="chgParcours(this.value)">
                                    <option value="">non renseigné</option>
                                    <% for (int i = 0; i < lesParcours.size(); i++) {
                                            Parcour parcour = lesParcours.get(i);
                                    %>
                                    <option value="<%=parcour.getIdParcours()%>_-_<%=parcour.getDistance()%>_-_<%=parcour.getDenivele()%>"><%=parcour.getNomParcours()%></option>  
                                    <%}%>
                                </select>
                            </div>
                            <div class="form-group col-lg-2 col-lg-offset-1">
                                <label>nb de tours: </label>
                                <input class="form-control" id="nbTours" type="number" value="1" min ="1" disabled="true" onchange="chgNbTours(this.value)"></input>
                            </div>
                            <div class="form-group col-lg-3 col-lg-offset-1">
                                <label>Distance (km): </label>
                                <input  class="form-control" id="distance" type="number"  min ="0" step="any" ></input>
                            </div>
                            <div class="form-group col-lg-3 col-lg-offset-1">
                                <label>Denivelé + (m): </label>
                                <input   class="form-control" id="denivele" type="number" min ="0"></input>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div id="typeMatch" class="row" style="display: none">
                        </div>
                        <div class="form-group col-lg-14 col-lg-offset-1">
                            <label>Comment:</label>
                            <textarea class="form-control" rows="5" id="comment" placeholder="..."></textarea>
                        </div>
                        <div class="form-group col-lg-10 col-lg-offset-3">
                            <button class="btn btn-primary btn-block" onclick="valSeance('<%=date.get(Calendar.DATE) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR)%>')">Ajouter Seance <span class="glyphicon glyphicon-ok"></span></button>
                        </div>
                    </div>
                    <p>(*) champs obligatoire</p>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="row">
</div>


