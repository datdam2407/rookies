<%-- 
    Document   : signup
    Created on : Feb 27, 2021, 5:25:03 PM
    Author     : MSI-PC
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Sign up Page</title>
    </head>
    <body>
        <style>
            h1{
                margin-left: 80%;
            }
        </style>

        <div class="container-fluid stylish-form ">		
            <div class="row mar20" >
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="inner-section">
                        <form method="POST" action="Signup">
                            <c:set var="errors" value="${requestScope.CREATEERROR}"/>
                            <div class="mar20 inside-form">
                                <h2 class="font_white text-center">SIGN UP</h2>

                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil "></i></span>
                                    <input type="text" class="form-control" name="txtFullname" value="${sessionScope.txtFullname}" placeholder="First Name...">
                                    <c:if test="${not empty errors.fullNameLengthErr}">
                                        <font color="red">
                                        ${errors.fullNameLengthErr}
                                        </font><br/>
                                    </c:if>
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope "></i></span>
                                    <input type="email" class="form-control" name="txtUsername" value="${sessionScope.txtUsername}" placeholder="Username...">
                                    <c:if test="${not empty errors.usernameLengthErr}">
                                        <font color="red">
                                        ${errors.usernameLengthErr}
                                        </font><br/>
                                    </c:if>
                                    <input type="hidden" class="form-control" name="txtUserEmail" value="${sessionScope.txtUsername}" placeholder="Email...">
                                    <c:if test="${not empty errors.usernameIsExisted}">
                                        <font color="red">
                                        ${errors.usernameIsExisted}
                                        </font><br/>
                                    </c:if>
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock "></i></span>
                                    <input type="address" class="form-control" name="txtAddress" value="${sessionScope.txtAddress}" placeholder="address...">
                                    <input type="phone" class="form-control" name="txtPhone" value="${sessionScope.txtPhone}" placeholder="phone...">

                                    <input type="password" class="form-control" name="txtPassword" value="${sessionScope.txtPassword}" placeholder="Password...">
                                    <c:if test="${not empty errors.passordLengthErr}">
                                        <font color="red">
                                        ${errors.passordLengthErr}
                                        </font><br/>
                                    </c:if>
                                    <input type="password" class="form-control" name="txtConfirm" value="${sessionScope.txtConfirm}"placeholder="Confirm password"/>
                                    <c:if test="${not empty errors.confirmNotMatched}">
                                        <font color="red">
                                        ${errors.confirmNotMatched}
                                        </font><br/>
                                    </c:if>
                                </div>
                                <div class="footer text-center">
                                    <input type="submit" name="btAction" class="btn btn-neutral btn-round btn-lg" value="Creat New Account" name="btAction" id="submit"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
        <h1> <a href="login.jsp">Cancel</a></h1>
    </body>


</html>
