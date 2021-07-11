<%-- 
    Document   : form_inscription
    Created on : 5 mars 2018, 20:34:28
    Author     : faycal
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
        Bienvenue ${user.login}
       
        <form name="inscription"  method="POST">
            <p>
                <label for="nom">Nom</label>
                <input type="text" name="nom" value="" size="10" id="nom" />
            </p>
            <p>
            <label for="adresse">Adresse</label>
            <input type="text" name="adresse" value="" size="10" id="adresse" />
            </p>
            <p>
            <label for="telephone">Téléphone</label>
            <input type="text" name="telephone" value="" size="10" id="tel" />
            </p>
            <p>
            <label for="email">E-mail</label>
            <input type="text" name="email" value="" size="10" id="email"/>
            </p>
            <p><label for="code_remise">Code Remise</label>
          <select name="code_remise" id="dc">
               <c:forEach items="${discount}" var="dc">
                <option>${dc}</option>
               </c:forEach>
          </select>
            </p>
            <p>
                <label for="cp">Code Postal</label>
            <select name="CP" id="cp">
                 <c:forEach items="${code}" var="cp">
                         <option>${cp}</option>
               </c:forEach>
            </select>
            
            </p>
            <input type="submit" value="Enregistrer" formaction="save.htm" />
        </form>
    </body>
</html>
