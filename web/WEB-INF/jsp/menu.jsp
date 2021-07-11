<%-- 
    Document   : menu
    Created on : 4 mars 2018, 16:59:09
    Author     : Matthieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    <body>
        
        <!-- Insertion de la banniere (duplication de code)  -->
        <%@ include file="banner.jsp" %>
        
        <h1>choisissez une opération dans la liste suivante</h1>
        
        <form name="form"  method="POST">
        
            <!--<input type="submit" formaction="list.htm"  value="Liste clients"  />
            <input type="submit" formaction="add.htm" value="Liste "  />
            <input type="submit" formaction="logout.htm" value="Se déconnecter"  />
            <input type="submit" formaction="formfind.htm" value="Rechercher"  />-->
            
            
            
            <input type="submit" formaction="list.htm"  value="Liste Clients" name="operation"  />
            <input type="submit" formaction="list.htm" value="Liste Produits" name="operation" />
            <input type="submit" formaction="list.htm" value="Liste Ventes" name="operation" />
            <input type="submit" formaction="formfind.htm" value="Rechercher Client"  />
        </form>
    </body>
</html>
