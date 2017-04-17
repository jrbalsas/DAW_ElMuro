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
        <p>Versión Servlet/JSP con validación de mensaje en cliente</p>

        <%-- If user not authenticated and forwarded from Auth Realm, show login form--%>
        <c:if test="${empty pageContext.request.remoteUser and not empty pageContext.request.getAttribute('javax.servlet.forward.servlet_path')}">
            <h1>Por favor, identifícate</h1>
            <form action="j_security_check" method="POST">
                Usuario: <input type="text" name="j_username"/><br/>
                Clave: <input type="password" name="j_password"/><br/>
                <input type="submit" value="Enviar"/>
            </form>
        </c:if>

        <ul>
            <li><a href="prueba.html">Pruebas básicas con JS</a></li>
            <li><a href="validadni.html">Validación DNI</a> con ER, uso de Jquery para modificación del DOM y controlador 
                JS basado en patrón objeto literal</li>                
            <%-- If used as login form, not show link to resource --%>
        <c:if test="${empty pageContext.request.getAttribute('javax.servlet.forward.servlet_path')}">
            <li><a href="muro">Acceso al muro (con validación de mensaje en cliente)</a></li>
        </c:if>
        </ul>

        <%-- Public content --%>
            
    </body>
</html>
