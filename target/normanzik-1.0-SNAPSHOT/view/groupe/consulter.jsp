<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="model.Groupe"%>
<!DOCTYPE html>
<html>
<head>
    <title>WebZik</title>
</head>
<body>
<h1>Consulter Groupe</h1>
<br/>


<%
    Groupe unGroupe = (Groupe)request.getAttribute("pGroupe");
%>
<%
out.println(unGroupe.getNom());
%>
</body>
</html>