<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
    </head>
    <body>
        
        <!-- Insertion de la banniere (duplication de code)  -->
        <%@ include file="banner.jsp" %>  
        
        <a href="menu.htm">Menu</a>
        <br/>
        <br/>
        <h1>${typeListe}</h1>
        
        <form name="detail" method="POST">
            <input type="hidden" value="${typeListe}" name="typeListe" /> <!-- Cet attribut va permettre au controlleur de savoir quel type d'objet est traité -->            
            <c:forEach var="colonne" items="${colonnes}" varStatus="row">
                <p>
                    <!-- Ici, grâce au principe de l'hydratation, nous pouvons alléger le code -->
                    <!-- De même, nous pouvons réutiliser cette page pour afficher les détails des clients, des produits et des ventes -->
                    <label for="${colonne}">${colonne}</label>
                    <input type="text" name="${colonne}" value="${resultat[row.index]}" size="30" id="${colonne}" />
                </p>
            </c:forEach>                        
        
            <input type="submit" value="Modifier" formaction="update.htm" />
            <input type="submit" value="Supprimer" formaction="delete.htm" />
            <c:if test="${typeListe.equals('Liste Clients')}">
            <input type="submit" value="Afficher les achats" formaction="achats.htm" />
            </c:if>
        </form>
    </body>
</html>
