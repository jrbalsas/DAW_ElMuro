<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            ${msgError}
            <input type="submit" name="Enviar">
        </form>
    </body>
</html>
