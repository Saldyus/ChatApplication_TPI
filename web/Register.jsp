<%-- 
    Document   : Register
    Created on : 17-apr-2018, 19.08.23
    Author     : Dinaro Salvatore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Registrati</h1>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
            <p>Username: 
                <input type="text" name="username" value="" />
            </p>
            <p>Password: 
                <input type="password" name="password" value="" />
            </p>
            <p>Nome visualizzato
                <input type="text" name="nome_v" value="" />
            </p>
            <input type="submit" value="REGISTRATI" />
        </form>
    </body>
</html>
