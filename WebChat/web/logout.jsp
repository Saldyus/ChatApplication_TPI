<%-- 
    Document   : logout
    Created on : 23-apr-2018, 16.58.43
    Author     : salva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
        <script type="text/javascript">
            setTimeout(
                function(){
                    window.location.href = "${pageContext.request.contextPath}/LoginServlet";
                }, 
                0
            );
        </script>
    </head>
    <body>
        <center><h1>Logout eseguito con successo!</h1></center>
    </body>
</html>
