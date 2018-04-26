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
                                        <a onclick="changeName(this.name)" href="#message" name="${user.username}">${user.username}</a>
                                        <span><i class="fa fa-circle" aria-hidden="true"></i> online</span>
                                    </div>
                                </li>
                            </c:forEach>


                            </ UTENTE LEFT />

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
                            <ul id="aggiorna">
                                <font color="black">
                                <c:forEach items="${messages}" var="message">
                                    <p>
                                        <c:if test="${message.getDS}.equals(\"SX\")">
                                        <li>
                                            <div class="rightside-right-chat">
                                                <span><i class="fa fa-circle" aria-hidden="true"></i></span><br><br>
                                                <p><c:out value="${message.text_m}"/></p>
                                            </div>
                                        </li>
                                    </c:if>
                                    <c:otherwise>
                                        <li>
                                            <div class="rightside-left-chat">
                                                <span><i class="fa fa-circle" aria-hidden="true"></i> nome ricevente <small>10:00 AM,Today</small> </span><br><br>
                                                <p><c:out value="${message.text_m}"/></p>
                                            </div>
                                        </li>
                                    </c:otherwise>
                                    </p>
                                </c:forEach>
                                </font>
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 right-chat-textbox">
                            <input type="text" id="text"><a><button class="" name="invia" type="submit"><i class="fa fa-arrow-right" aria-hidden="true"></i></button></a>
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
            var username;
            function changeName(name) {
                document.getElementById("nome").innerHTML = name;
                username = name;
                int = 1;
            }
            window.setInterval(function () {
                if (int == 1) {
                    loadMessage(name);
                }
            }, 1000);
            function loadMessage(name) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/MessageServlet",
                    data: "?mitt=${username_mitt}&dest=" + username,
                    dataType: "html",
                    success: function (risposta) {
                        console.log("${username_mitt}");
                        console.log(username);
                        console.log(risposta);
                    },
                    error: function () {
                        alert("Chiamata fallita!!!");
                    }
                });
            }
        </script>
    </body>
</html>