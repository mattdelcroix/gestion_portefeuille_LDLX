<%-- 
    Document   : resultat
    Created on : 4 mars 2018, 20:29:48
    Author     : Matthieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultat</title>
    </head>
    <body>
        
        <!-- Insertion de la banniere (duplication de code)  -->
        <%@ include file="banner.jsp" %>       
        
        <a href="menu.htm">Menu</a>
        <br/>
        <br/>
        <h1>${typeListe}</h1>
        
        <form name="Ajouter" action="form.htm"  method="POST">
            <input type="hidden" name="typeListe" value="${typeListe}" />
            <input type=submit  value="Ajouter"  />
        </form>
                
        <table border="1" cellpadding="10">    
               <TR>
                    <c:forEach items="${colonnes}" var="col">
                    <TH>${col}</TH> 
                    </c:forEach>
                    <TH>Action</TH>
                </TR>                
            
            <c:forEach items="${liste}" var="client" varStatus="row" >
                <TR>
                  <c:forEach items="${client}" var="cli" >  
                    <TD>${cli}</TD>
                  </c:forEach>
                    <TD>
                        <form name="Result" action="detail.htm"  method="POST">
                            <input type="hidden" name="operation" value="${typeListe}" />
                            <input type=hidden name="num"  value="${liste[row.index][0]}"/>
                            <input type=submit  value="Détail"  />
                        </form>
                    </TD>
                </TR>
            </c:forEach>
        </table>
        
    </body>
</html>
