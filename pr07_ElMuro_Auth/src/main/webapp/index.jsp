<%-- Ejemplo de página de Inicio que también funciona como formulario de login
     Es necesario JSTL para mostrar el formulario de login solo si hay que identificar al usuario
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>El Muro</h1>
        <p>Versión con restricciones de acceso y Realm JDBC</p>

        <%-- If user not authenticated and forwarded from Auth Realm, show login form--%>
        <c:if test="${empty pageContext.request.remoteUser and not empty pageContext.request.getAttribute('javax.servlet.forward.servlet_path')}">
            <h1>Por favor, identifícate</h1>
            <form action="j_security_check" method="POST">
                Usuario: <input type="text" name="j_username"/><br/>
                Clave: <input type="password" name="j_password"/><br/>
                <input type="submit" value="Enviar"/>
            </form>
        </c:if>

        <%-- If used as login form, not show link to resource --%>
        <c:if test="${empty pageContext.request.getAttribute('javax.servlet.forward.servlet_path')}">
            <a href="muro">Acceso al muro</a>
        </c:if>

        <%-- Public content --%>
            
    </body>
</html>
