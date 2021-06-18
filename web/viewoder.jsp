<%-- 
    Document   : viewoder
    Created on : Mar 11, 2021, 7:26:34 PM
    Author     : MSI-PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HIstory Page</title>
    </head>
    <body>
        <style>
            body{
                background-color: darksalmon;
                background-size: cover;
                background-repeat: no-repeat;
            }
            #order_box
            {
                width: 1700px;
                background-color: darksalmon;
                margin: auto;
                border: 4px solid #e8d0a9;
                border-radius: 5px; 
            }

            #title{
                text-align: center;
                margin-top: 100px;
                font-size: 80px;
                font-family: Georgia, 'Times New Roman', Times, serif;
                color: #6d929b;
                margin-bottom: 30px;
            }

            #order_box h2
            {
                margin-left: 100px;
                color: #d8e6df;
            }

            #back
            {
                float: left;
                width: 1700px;
                margin: 50px auto;
            }

            #login{
                text-decoration: none;
                font-family: Georgia, 'Times New Roman', Times, serif;
                color: #6d929b;
                padding: 20px 50px;
                background-color: #d8e6df;
                border-radius: 2px;
                border: 2px solid #6d929b;
                text-align: center;
                margin-left: 735px;
            }

            #page_table{
                border-radius: 10px;
                margin: 50px auto 0px;

                width: 1680px;
                border: solid;
                background-color:darksalmon;
            }

            #page_table th{
                font-family:Georgia, 'Times New Roman', Times, serif;
                font-size: 25px;
                padding: 5px;
            }

            #page_table td{
                font-family:Georgia, 'Times New Roman', Times, serif;
                font-size: 20px;
                padding: 5px;
                text-align:center;
            }

            #pic
            {
                width: 1000px;
                height: 600px;
            }
        </style>
        <div id="title">Thanks You!!</div>
        <div id="order_box">
            <h2 id="name">Name: ${sessionScope.cusName}</h2>
            <h2>Phone: ${sessionScope.cusPhone}</h2>
            <h2>Address: ${sessionScope.cusAddress}</h2>
            <h2>Rental date: ${sessionScope.rentalDate}</h2>
            <h2>Return date: ${sessionScope.returnDate}</h2>
            <h2>Total bill: ${requestScope.total}$</h2>
            <table id="page_table" border="1">

                <tbody>
                    <c:forEach items="${requestScope.orderDetail}" var="dto">
                        <tr>
                            <td>
                                <img id="pic" src="${dto.image}">
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br/>   
        </div>
        <div id="back">
            <a id="login" href="Page">Back to home page</a>
        </div>
    </body>
</html>

