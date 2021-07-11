<%-- 
    Document   : deconnexion
    Created on : 1 juin 2020, 17:09:38
    Author     : faycal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deconnexion</title>
    </head>
    <body>
        <h1>Vous etes deconnecte</h1>
        <%response.sendRedirect("/Bdd_Spring_HI/");%>
    </body>
</html>
