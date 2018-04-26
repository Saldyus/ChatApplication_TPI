<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : index
    Created on : 17-apr-2018, 18.58.16
    Author     : Dinaro Salvatore
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/custom.css">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="container main-section">
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-12 left-sidebar">
                    <div class="input-group searchbox">
                        <input class="form-control" placeholder="Search" name="srch-term" id="srch-term" type="text">
                        <div class="input-group-btn">
                            <button class="btn btn-default search-icon" type="submit" name="search"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                    <div class="left-chat">
                        <ul>
                            <p>
                                <font color="white">
                                Accesso effettuato da: <br/>
                                </font>
                                <font color="orange">
                                <c:out value="${username_mitt}" />
                                </font>
                            </p>
                            <font color="white">Gruppi: <br/>
                            <c:forEach items="${groups}" var="gruop">
                                <p id="gruops">
                                    <a id="mex_groups"><c:out value="${group.nameG}" /></a>
                                </p>
                            </c:forEach>
                            <font color="white">Utenti Database:<br/></font>
                            <c:forEach items="${users}" var="user">
                                <li>
                                    <div class="chat-left-img">
                                        <img src="image/businessman.png">
                                    </div>
                                    <div class="chat-left-detail">
                                        <a href="#" onclick="changeName(this.name)" name="${user.username}">${user.username}</a>
                                        <span><i class="fa fa-circle" aria-hidden="true"></i> online</span>
                                    </div>
                                </li>
                            </c:forEach>
                            <a href="logout.jsp">Logout</a>
                        </ul>
                    </div>
                </div>
                <div class="col-md-9 col-sm-9 col-xs-12 right-sidebar">
                    <div class="row">
                        <div class="col-md-12 right-header">
                            <div class="right-header-img">
                                <img src="
                                     FOTO PROFILO ">
                            </div>
                            <div class="right-header-detail">
                                <p id="nome"> NOME RICEVENTE </p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 right-header-contentChat">
                            <font color ="black">
                            <ul id="aggiorna">

                            </ul>
                            </font>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 right-chat-textbox">
                            <input type="text" id="message_text">
                            <a href="#"><button onclick="sendMessage()" name="invia" type="submit"><i class="fa fa-arrow-right" aria-hidden="true"></i></button></a>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                var height = $(window).height();
                $('.left-chat').css('height', (height - 92) + 'px');
                $('.right-header-contentChat').css('height', (height - 163) + 'px');
            });
        </script>
        <script type="text/javascript">
            var int = 0;
            var username = "";

            function changeName(name) {
                document.getElementById("aggiorna").innerHTML = "";
                document.getElementById("nome").innerHTML = name;
                username = name;
                int = 1;
            }

            window.setInterval(function () {
                if (int == 1) {
                    loadMessage();
                }
            }, 500);

            function loadMessage() {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/MessageServlet",
                    data: "mitt=${username_mitt}&dest=" + username,
                    dataType: "json",
                    success: function (risposta) {
                        var obj = risposta;
                        var html = "";
                        for (i in obj.messages) {
                            var DS = obj.messages[i].DS;
                            if (DS === "DX") {
                                html += "<li>"
                                        + "<div class=\"rightside-right-chat\">"
                                        + "<span><small>" + obj.messages[i].data_m + "</small> Io <i class=\"fa fa-circle\" aria-hidden=\"true\"></i></span><br/><br/>"
                                        + "<p>" + obj.messages[i].text_m + "</p>"
                                        + "</div>"
                                        + "</li>";
                            } else {
                                html += "<li>"
                                        + "<div class=\"rightside-left-chat\">"
                                        + "<span> " + obj.messages[i].mitt + "<small> " + obj.messages[i].data_m + " </small><i class=\"fa fa-circle\" aria-hidden=\"true\"></i></span><br/><br/>"
                                        + "<p>" + obj.messages[i].text_m + "</p>"
                                        + "</div>"
                                        + "</li>";
                            }
                            document.getElementById("aggiorna").innerHTML = html;
                        }
                    },
                    error: function () {
                        alert("Chiamata fallita!!!");
                    }
                });
            }


            function sendMessage() {
                var text = $("#message_text").val();
                document.getElementById("message_text").value = "";
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/SendMessageServlet",
                    data: "mitt=${username_mitt}&dest=" + username + "&text=" + text,
                    dataType: "json",
                    success: function (risposta) {
                        
                    },
                    error: function () {
                        
                    }
                });
            }
        </script>
    </body>
</html>