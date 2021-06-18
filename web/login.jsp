<%-- 
    Document   : login
    Created on : Feb 26, 2021, 1:58:10 PM
    Author     : MSI-PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <title>My Awesome Login Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
    </head>

    <script>
        function validateForm() {
            var x = document.forms["myForm"]["txtUsername"].value;
            if (x == "") {
                alert("Username must be filled out");
                return false;
            }
            var y = document.forms["myForm"]["txtPassword"].value;
            if (y == "") {
                alert("Password must be filled out");
                return false;

        }
        }

    </script>

    <body>

        <div class="container h-100">
            <div class="d-flex justify-content-center h-100">
                <div class="user_card">
                    <div class="d-flex justify-content-center">
                        <div class="brand_logo_container">
                            <img src="https://nganhangphapluat.thukyluat.vn/images/CauHoi_Hinh/9eb6abaa-8cda-456c-ad66-26ba4da23ffe.jpg" class="brand_logo" alt="Logo">
                        </div>
                    </div>         
                    <div class="d-flex justify-content-center form_container">
                        <form action="login" onsubmit="return validateForm()" method="POST">
                            <div class="input-group mb-3">
                                <div class="input-group-append">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" name="txtUsername" class="form-control input_user" value="" placeholder="email">
                            </div>
                            <div class="input-group mb-2">
                                <div class="input-group-append">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" name="txtPassword" class="form-control input_pass" value="" placeholder="Password">
                            </div>
                            <font color="red"> ${requestScope.ERROR}</font>
                           
                            <div class="g-recaptcha"
                                 data-sitekey="6LdplGgaAAAAADeBHe-PVKOD4F7vutI_TLnjqx9w
                                 "></div>
                                <font color="red"> ${requestScope.verify}</font>
                     <input type="hidden" name="txtRentDate" value="${sessionScope.txtRentDate}" min="${sessionScope.currentDate}" style="width: 150px"/><br/>
                    <input type="hidden" value="${sessionScope.name}" name="txtName"  style="width: 100px"/> 
                    <input type="hidden" name="txtPayDate" value="${sessionScope.txtPayDate}" min="${sessionScope.currentDate}" style="width: 150px"/><br/>
                    <input type="hidden" name="txtCategory" value="${sessionScope.category}" /><br/>
                   
                            <div class="d-flex justify-content-center mt-3 login_container">
                                <button type="submit" name="btAction" class="btn login_btn">Login</button>
                            </div>
                        </form>
                    </div>

                    <div class="mt-4">
                        <div class="d-flex justify-content-center links">
                            <h4>Don't have an account?</h4> <a href="LoadUser" class="ml-2"><h4>Sign Up</h4></a>

                        </div>

                    </div>
                        
                </div>
            </div>


    </body>
</html>
