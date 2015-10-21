

<%@page import="model.Match"%>
<%@page import="java.util.ArrayList"%>

<datalist id="listJoueur">
    <option value="test"></option>
    <option value ="coucou"></option>
</datalist> 
<%ArrayList<Match> lesMatchs = (ArrayList<Match>) session.getAttribute("lesMatchs");

 int nbMatch = lesMatchs.size();
 for (int i = 0; i < nbMatch; i++) {%>
<div class="row">
    <div class="jumbotron match col-lg-14 col-lg-offset-1">
        <div class="col-lg-5">
            <label >Joueur(s) : </label>
            <%int nbJoueur =lesMatchs.get(i).getLesJoueurs().size();
            for (int j = 0; j < nbJoueur ; j++) {
                    String joueur = lesMatchs.get(i).getLesJoueurs().get(j);
            %>
            <div class="col-lg-14">
                <input class="form-control"  id="Joueur_<%=i%>_<%=j%>" onchange="changePersonne(<%=i%>,<%=j%>,'Joeur',this.value)" value ="<%=joueur%>" list="listJoueur"  >
                </input>
            </div>
            <div class="col-lg-2">
                <a href="#" <%if(nbJoueur>1){%>onclick="delPersonne(<%=i%>,<%=j%>,'Joeur')" style="color: red"<%}else{%>style="color: gray" <%}%>><span class="glyphicon glyphicon-minus-sign"></span></a>
            </div>
            <%}%>             
            <a href="#" onclick="addPersonne(<%=i%>,'Joeur')"><span class="glyphicon glyphicon-plus-sign"></span></a>
        </div>
        <div class="col-lg-5 ">
            <h4 class="text-center">VS</h4>
            <div class="col-lg-5 col-lg-offset-3"><input onchange="changeScore(<%=i%>,'Joueur',this.value)" class="form-control" type="number" min="0" size="3" value="<%=lesMatchs.get(i).getScoreJoueurs()%>"></input></div>
            <div class="col-lg-1"></div>
            <div class="col-lg-5"><input onchange="changeScore(<%=i%>,'Adv',this.value)" class="form-control" type="number" min="0" size="3" value="<%=lesMatchs.get(i).getScoreAdversaire()%>"></input></div>
        </div>
        <div class="col-lg-5">
            <label >Adversaire(s) : </label>
            <%int nbAdv =lesMatchs.get(i).getLesAdversaires().size();
            for (int j = 0; j < nbAdv; j++) {
                    String adv = lesMatchs.get(i).getLesAdversaires().get(j);
            %>
            <div class="col-lg-14">
                <input class="form-control" id="Adv_<%=i%>_<%=j%>" list="listJoueur" value="<%=adv%>"  onchange="changePersonne(<%=i%>,<%=j%>,'Adv',this.value)">
                </input>
            </div>
            <div class="col-lg-2">
                <a href="#"  <%if(nbAdv>1){%>onclick="delPersonne(<%=i%>,<%=j%>,'Adv')" style="color: red"<%}else{%>style="color: gray" <%}%>><span class="glyphicon glyphicon-minus-sign"></span></a>
            </div>
            <%}%>             
            <a href="#" onclick="addPersonne(<%=i%>,'Adv')"><span class="glyphicon glyphicon-plus-sign"></span></a>
        </div>
    </div> 
        <a href="#" <%if(nbMatch>1){%>onclick="delMatch(<%=i%>)" style="color: red"<%}else{%>style="color: gray" <%}%>><span class="glyphicon glyphicon-minus-sign"></span></a>
</div>
    <%}%>

<div class="row text-center">
    <a href="#" onclick="addMatch()"><span class="glyphicon glyphicon-plus-sign"></span></a>
</div>



