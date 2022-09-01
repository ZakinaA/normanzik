<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="model.Groupe"%>
<%@ page import="form.FormGroupe" %>
<%@ page import="model.Genre" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>WebZik</title>
</head>
<body>
<h1>Ajouter Groupe</h1>
<br/>


<%
    //Client client=(Client)request.getAttribute("client");
    FormGroupe form = (FormGroupe)request.getAttribute("form");
%>

<form class="form-inline" action="ajouter" method="POST">
    <label for="nom">Nom : </label>
    <input id="nom" type="text" name="nom"  size="70" maxlength="70">
    <span style="color: blue;">${form.erreurs['nom']}</span>
    <br />
    </br>
    </br>
    <label for="dateCreation">Date de création : </label>
    <input id="dateCreation"  type="text"  name="dateCreation" size="10" maxlength="10">
    <span style="color: blue;">${form.erreurs['dateCreation']}</span>
    </br>

    <%-- Champ Liste des genres --%>
    <label for="genre">Genre : </label>
    <select name="idGenre">
        <%
            ArrayList<Genre> lesGenres = (ArrayList)request.getAttribute("pLesGenres");
            for (int i=0; i<lesGenres.size();i++){
                Genre g = lesGenres.get(i);
                out.println("<option value='" + g.getId()+"'>" + g.getLibelle()+"</option>" );
            }
        %>
    </select>
    </br>
    </br>
    <input type="submit" name="valider" id="valider" value="Valider"/>
</form>
</body>
</html>