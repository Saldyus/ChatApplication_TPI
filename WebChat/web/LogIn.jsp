<%-- 
    Document   : LogIn
    Created on : 17-apr-2018, 19.08.11
    Author     : Dinaro Salvatore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Effetua il Login</h1>
        <form action="${pageContext.request.contextPath}/IndexServlet" method="POST">
            <p>Username: 
                <input type="text" name="username" value="" />
            </p>
            <p>Password: 
                <input type="password" name="password" value="" />
            </p>
            <input type="submit" value="ACCEDI" />
        </form>
        <form action="${pageContext.request.contextPath}/RegisterServlet" method="GET">
            <input type="submit" value="Registrati" />
        </form>
    </body>
</html>
