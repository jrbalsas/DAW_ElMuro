<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Mensajes</title>
    </head>
    <body>
        <h1>Muro de ${identificador}</h1>
        <h2>Mensajes</h2>
        <ul>
            <c:forEach var="m" items="${mensajes}">
                <li>${fn:escapeXml(m.identificador)}: ${fn:escapeXml(m.texto)}</li>
            </c:forEach>
        </ul>
        <form method="POST">
            <input path="texto" name="mensaje" value="${mensaje}" placeholder="Mensaje"/>
            
            <input type="submit" name="Enviar">
        </form>
           
            <a href="<c:url value="/Logout"/>">Desconectar</a>
    </body>
</html>
