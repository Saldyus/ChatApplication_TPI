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
        <link rel="stylesheet" type="text/css" href="custom.css">
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
                            <button class="btn btn-default search-icon" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                    <div class="left-chat">
                        <ul>
                            
                            <font color="white"><div id="user">User</div></font>
                            
                            </ UTENTE LEFT />






                            
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
                                <p> NOME RICEVENTE </p>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 right-header-contentChat">
                            <ul>







                                </ MESSAGGIO IO 

                                //MESSAGGIO UTENTE RICEVENTE />









                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 right-chat-textbox">
                            <input type="text"><a href="#"><i class="fa fa-arrow-right" aria-hidden="true"></i></a>
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
    </body>
</html>