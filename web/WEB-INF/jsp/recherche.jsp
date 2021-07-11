<%-- 
    Document   : recherche
    Created on : 7 juin 2019, 11:52:07
    Author     : Matthieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche</title>
    </head>
    <body>
        
        <!-- Insertion de la banniere (duplication de code)  -->
        <%@ include file="banner.jsp" %>  
        
        <a href="menu.htm">Menu</a>
        <br/>
        <br/>
        
        <form name="recherche" action="find.htm" method="POST">
           <p>
                <label for="id">Id du client</label>
                <input type="text" name="id"  size="30" />
            </p>
           <p>
                <label for="nom">Nom du client</label>
                <input type="text" name="nom"  size="30" />
            </p>
            <input type="submit" value="rechercher"  />
        </form>
        
    </body>
</html>
