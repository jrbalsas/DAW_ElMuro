<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <li>${fn:escapeXml(m.identificador)}: ${fn:escapeXml(m.mensaje)}</li>
            </c:forEach>
        </ul>
        <form method="POST">
            <input type="text" name="mensaje" value="${mensaje}" placeholder="Mensaje">
            ${error}
            <input type="submit" name="Enviar">
        </form>
           
            <a href="Logout">Desconectar</a>
    </body>
</html>
