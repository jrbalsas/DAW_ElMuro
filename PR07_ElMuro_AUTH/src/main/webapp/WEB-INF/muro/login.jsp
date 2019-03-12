<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>El Muro</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body class="container">
        <div class="row">
            <div class="col-6">
                <h1>Identificación de usuario</h1>
                <c:if test="${param.error}">
                    <p class="alert alert-danger">Credenciales incorrectas</p>
                </c:if>
                <form action="j_security_check" method="POST">
                    <label>Usuario</label><input class="form-control" type="text" name="j_username"/>
                    <label>Clave</label><input class="form-control" type="password" name="j_password"/>
                    <input class="btn btn-primary" type="submit" name="Acceder"/>
                </form>            
            </div>
        </div>
    </body>
</html>   