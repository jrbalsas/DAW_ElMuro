<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>El muro</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        
    </head>
    <body class="container">
        <main class="row justify-content-center">
            <div class="col-6">
                <h1>Por favor, identifícate</h1>
                <c:if test="${param.error}">
                    <p class="alert alert-danger">Credenciales incorrectas</p>
                </c:if>
                <form action="j_security_check" method="POST">
                    Usuario: <input type="text" name="j_username" class="form-control"/><br/>
                    Clave: <input type="password" name="j_password" class="form-control"/><br/>
                    <input type="submit" value="Enviar" class="form-control"/>
                </form>
            </div>
        </main>
    </body>
</html>
