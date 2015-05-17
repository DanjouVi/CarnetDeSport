
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="model.Utilisateur"%>

<nav class="navbar navbar-inverse">    
       <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="index.jsp">Carnet de sport</a>
                    </div>
                    <div>
                        <% String pageCourant = (String) session.getAttribute("pageCourant");
                        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");%>
                        <ul class="nav navbar-nav">
                            <li<%if(pageCourant.equals("index")){%> class="active"<%}%>><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                            <%if(utilisateur!=null){%>
                                <li<%if(pageCourant.equals("calendar")){%> class="active"<%}%>><a href="htmlCalendar"><span class="glyphicon glyphicon-calendar"></span> Calendrier</a></li>
                                <li<%if(pageCourant.equals("sports")){%> class="active"<%}%>><a href="ResetSport">Sports</a></li>
                                
                                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-globe"></span> Parcours<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Afficher  <span class="glyphicon glyphicon-eye-open"></span></a></li>
                                        <li><a href="#">Nouveau  <span class="glyphicon glyphicon-plus"></span></a></li>
                                    </ul>
                                </li>
                                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-stats"></span> Objectifs<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Afficher  <span class="glyphicon glyphicon-eye-open"></span></a></li>
                                        <li><a href="#">Nouveau  <span class="glyphicon glyphicon-plus"></span></a></li>
                                    </ul>
                                </li>
                            <%}%>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <%if(utilisateur==null){%>
                                <li <%if(pageCourant.equals("signUp")){%> class="active"<%}%>><a href="SignUp.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>  
                                <li<%if(pageCourant.equals("login")){%> class="active"<%}%>><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Log in</a></li>       
                            <%}else{%>
                                <li <%if(pageCourant.equals("signUp")){%> class="active"<%}%>><a href="CompteUser.jsp"><span class="glyphicon glyphicon-user"></span> ${utilisateur.pseudo}</a></li>
                                <li><a href="LogOut"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
                            <%}%>
                            
                        </ul>
                    </div>
                </div>
            </nav>