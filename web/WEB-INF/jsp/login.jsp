<%-- 
    Document   : login
    Created on : 1 juin 2020, 11:40:47
    Author     : faycal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        <form method="POST" action="j_security_check">
<table>
<tr>
<td>Utilisateur :</td>
<td><input type="text" name="j_username"></td>
</tr>
<tr>
<td>Mot de passe :</td>
<td><input type="password" name="j_password"  ></td>
</tr>
<tr>
<td><input type="submit" value="Entrer" ></td>
<td><input type="reset" value="Annuler"></td>
</tr>
</table>
</form>
    </body>
</html>
