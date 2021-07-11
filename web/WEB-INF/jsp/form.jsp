<%-- 
    Document   : form_inscription
    Created on : 5 mars 2018, 20:34:28
    Author     : Matthieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enregistrement</title>
    </head>
    <body>
        
        <!-- Insertion de la banniere (duplication de code)  -->
        <%@ include file="banner.jsp" %>       
        
        <a href="menu.htm">Menu</a>
        <br/>
        <br/>
        <h1>Ajouter un élément dans la ${typeListe}</h1>
        
        <form name="save" action="save.htm" method="POST">
            <input type="hidden" value="${typeListe}" name="typeListe" /> <!-- Cet attribut va permettre au controlleur de savoir quel type d'objet est traité -->            
            <c:forEach var="colonne" items="${colonnes}" varStatus="row">
                <p>
                    <!-- Ici, grâce au principe de l'hydratation, nous pouvons alléger le code -->
                    <!-- De même, nous pouvons réutiliser cette page pour afficher les détails des clients, des produits et des ventes -->
                    <label for="${colonne}">${colonne}</label>
                    <input type="text" name="${colonne}" size="30" id="${colonne}" />
                </p>
            </c:forEach>                        
        
            <input type="submit" value="Ajouter"/>
        </form>
            
        <!-- Pour le client, il manque le code remise et le zip code -->
            
    </body>
</html>
