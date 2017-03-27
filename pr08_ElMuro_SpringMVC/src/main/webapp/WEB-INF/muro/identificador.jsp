<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Cambio de usuario</title>
    </head>
    <body>
        <h1>Bienvenido al Muro</h1>
        <h2>Introduce tu nombre</h2>
        <form method="POST">
            <input type="text" value="${pageContext.request.remoteUser}" name="identificador" placeholder="Identificador">
            <input type="submit" name="Enviar">
        </form>
    </body>
</html>
