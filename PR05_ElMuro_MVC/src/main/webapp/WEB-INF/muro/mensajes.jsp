<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>El Muro</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body class="container">
        <h1>Muro de ${prefs.usuario}</h1>
        <h2>Mensajes</h2>
        <ul class="list-group">
            <c:forEach var="m" items="${muro.mensajes}">
                <li class="list-group-item"><strong>${fn:escapeXml(m.identificador)}:</strong> ${fn:escapeXml(m.mensaje)}</li>
            </c:forEach>
        </ul>
        <div class="row">
            <div class="col-md-6">
                <h2>Nuevo mensaje</h2>
                <form method="POST" >
                    <input type="text" name="mensaje" value="${mensaje}" placeholder="Mensaje" class="form-control">
                    <span class="text-danger">${msgError}</span>
                    <input type="submit" name="Enviar" class="form-control">
                </form>
            </div></div>
    </body>
</html>
