<%-- 
    Document   : viewModal
    Created on : 2 avr. 2015, 18:59:48
    Author     : vivi
--%>

<%@page import="model.Seance"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

	
<% 
Calendar date = (Calendar) request.getAttribute("date");
ArrayList<Seance> lesSeances = (ArrayList<Seance>) request.getAttribute("lesSeances");
String[] nomMois = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet",
        "Août", "Septembre", "Octobre", "Novembre", "Décembre" };
String strdate="";

if (date != null) {
    strdate = date.get(Calendar.DATE) +" "+ nomMois[date.get(Calendar.MONTH)] +" "+date.get(Calendar.YEAR); 
}
%>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">&times;</span></button>
    <h3 class="modal-title" id="gridSystemModalLabel">Les Séance du : <%=strdate%></h3>
</div>

<div class="row col-sm-14 col-sm-offset-1">
    <%if(lesSeances.size()==0){%>
    <h5>Pas de séances enregistrée</h5>
    <%}%>
    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="panel panel-default">
            <div class="panel-heading"  data-toggle='collapse' href="#bodyNewSeance">
                <h4>&nbsp;&nbsp;Nouvelle Séance</h4>
            </div>
            <div id="bodyNewSeance" class="panel-collapse collapse in">
                <div class="panel-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                </div>
            </div>
        </div>
    </div>
    
</div>
<div class="row">
   coucou 
</div>


