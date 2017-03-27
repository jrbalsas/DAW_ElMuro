<%--! Adapted JSP for SpringMVC form tags --%>
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
        <p>Solo mensajes entre 2 y 100 caracteres de longitud</p>
        <form:form modelAttribute="mensaje" method="POST">
            <form:input path="texto" placeholder="Mensaje"/>
            <p><form:errors path="texto" cssStyle="color: red;"/></p>
            <input type="submit" name="Enviar">
        </form:form>
           
            <a href="<c:url value="/Logout"/>">Desconectar</a>
    </body>
</html>
