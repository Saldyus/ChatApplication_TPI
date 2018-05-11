<%-- 
    Document   : index
    Created on : 13-mar-2018, 9.26.33
    Author     : Dinaro Salvatore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>



<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <style>
            body {font-family: Arial, Helvetica, sans-serif;}

            /* Full-width input fields */
            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            /* Set a style for all buttons */
            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }

            button:hover {
                opacity: 0.8;
            }

            /* Extra styles for the cancel button */
            .cancelbtn {
                width: auto;
                padding: 10px 18px;
                background-color: #f44336;
            }

            /* Center the image and position the close button */
            img{
                width: 280px;
                height: 280px;
                border-radius: 50%;
            }

            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
                position: relative;
            }

            img.avatar:hover {
                opacity: 0.5;
            }

            .container {
                padding: 16px;
            }

            span.psw {
                float: right;
                padding-top: 16px;
            }

            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
                padding-top: 60px;
            }

            /* Modal Content/Box */
            .modal-content {
                background-color: #fefefe;
                margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
                border: 1px solid #888;
                width: 80%; /* Could be more or less, depending on screen size */
            }

            /* The Close Button (x) */
            .close {
                position: absolute;
                right: 25px;
                top: 0;
                color: #000;
                font-size: 35px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: red;
                cursor: pointer;
            }

            /* Add Zoom Animation */
            .animate {
                -webkit-animation: animatezoom 0.6s;
                animation: animatezoom 0.6s
            }

            @-webkit-keyframes animatezoom {
                from {-webkit-transform: scale(0)} 
                to {-webkit-transform: scale(1)}
            }

            @keyframes animatezoom {
                from {transform: scale(0)} 
                to {transform: scale(1)}
            }

            /* Change styles for span and cancel button on extra small screens */
            @media screen and (max-width: 300px) {
                span.psw {
                    display: block;
                    float: none;
                }
                .cancelbtn {
                    width: 100%;
                }
            }
        </style>
    </head>
    <body>

        <h2>Chat Web</h2>

        <button onclick="document.getElementById('login').style.display = 'block'" style="width:auto;">Login</button>
        <button onclick="document.getElementById('register').style.display = 'block'" style="width:auto;">Register</button>

        <div id="login" class="modal">

            <form class="modal-content animate" action="${pageContext.request.contextPath}/IndexServlet" method="POST">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('login').style.display = 'none'" class="close" title="Close Modal">&times;</span>

                    <div class="container">
                        <label for="username"><b>Username</b></label>
                        <input type="text" placeholder="Enter Username" name="username" required>

                        <label for="password"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" required>

                        <button type="submit">Login</button>

                    </div>

                    <div class="container" style="background-color:#f1f1f1">
                        <button type="button" onclick="document.getElementById('login').style.display = 'none'" class="cancelbtn">Cancel</button>
                    </div>
                </div>
            </form>
        </div>

        <div id="register" class="modal">

            <form class="modal-content animate" action="${pageContext.request.contextPath}/LoginServlet" method="POST">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('register').style.display = 'none'" class="close" title="Close Modal">&times;</span>

                    <input type="file" id="foto" accept=".jpg, .jpeg, .png, .gif" class="avatar" style="position: absolute; border-radius:50%; width: 280px; height: 280px; opacity: 0; cursor: pointer;" />
                    <img src="image/user.png" class="avatar" id="photo" alt="image">

                    <div class="container">
                        <label for="username"><b>Username</b></label>
                        <input type="text" placeholder="Enter Username" name="username" required>

                        <label for="password"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" required>

                        <label for="nome_v"><b>Nome visualizzato</b></label>
                        <input type="text" placeholder="Enter name visualized" name="nome_v" required>

                        <button type="submit">Register</button>

                    </div>

                    <div class="container" style="background-color:#f1f1f1">
                        <button type="button" onclick="document.getElementById('register').style.display = 'none'" class="cancelbtn">Cancel</button>
                    </div>
                </div>
            </form>
        </div>
        <script>
            // Get the modal
            var modal = document.getElementById('login');
            var modal2 = document.getElementById('register');
            
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $('#photo').attr('src', e.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                }
            }

            $("#foto").change(function () {
                readURL(this);
            });

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                } else {
                    if (event.target == modal2) {
                        modal2.style.display = "none";
                    }
                }

            }
        </script>
    </body>
</html>