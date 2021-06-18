<%-- 
    Document   : viewcart
    Created on : Mar 7, 2021, 7:52:44 PM
    Author     : MSI-PC
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
        <link rel="stylesheet" href="admincss.css">
        <script>
            function myFunction() {
                var txt;
                var r = confirm("Do you want to remove");
                if (r == true) {
                    return true;
                } else {
                    return false;
                }
                document.getElementById("demo").innerHTML = txt;
            }
        </script>
    </head>
    <style>
        body{
            background-color: darksalmon;
            background-size: cover;
            background-repeat: no-repeat;
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
            text-align:center;
        }
        #page_table td{
            font-family:Georgia, 'Times New Roman', Times, serif;
            font-size: 20px;
            padding: 5px;
            text-align:center;
        }
        #title{
            text-align: center;
            margin-top: 20px;
            font-size: 80px;
            font-family: Georgia, 'Times New Roman', Times, serif;
            color: #6d929b;
            margin-bottom: 30px;
        }
        #button{
            text-align: center;
            padding: 10px 15px;
            border-radius: 5px;

            color: black;
            border: none;
            outline: none;
        }
        #form{
            width: 100%;
            max-width: 400px;
            margin: 20px auto;
            background-color: darksalmon;
            padding: 15px;
            border: 2px;
            border-radius: 10px;
            opacity: 0.7;
        }
        #form_b{
            width: 60%;
            max-width: 250px;
            margin-left:1480px;
            background-color: darksalmon;
            padding: 15px;
            border: 2px;
            border-radius: 10px;
            opacity: 0.7;
        }
        #box_title{
            font-family: Georgia, 'Times New Roman', Times, serif;
            color: yellow;
            opacity: 1;
        }
        #text_field{
            margin-bottom: 10px;
        }
        #text_field input{
            padding: 7.5px 15px;
            width: 92%;
            border: 1px solid #cccccc;
            outline: none;
            background-color: #e8d0a9;
        }
        p{
            font-size: 120%;
            margin-left: 20px;
            font-weight: bold;
        }

        #button{
            margin: 10px auto;
        }
        #button input{
            padding: 10px 135px;
            border-radius: 5px;
            background-color: #c1dad6;
            color: #6d929b;
            border: none;
            outline: none;
        }
        body h2{
            margin: 50px auto;
            text-align: center;
            font-size: 400%;
        }
    </style>
    <h1>${sessionScope.shCart.customerName}'s Cart</h1>
    <p>${sessionScope.notify}</p>
    <c:if test="${not empty sessionScope.shCart.cart}">
        <table id="page_table" border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>I want More !!!</th>
                    <th>I Don't Want to Book This</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${sessionScope.shCart.cart.values()}" var="DTO" varStatus="counter">
                    <tr>
                        <td>${DTO.carID}</td>
                        <td>${DTO.name}</td>
                        <td>
                            <img src="${DTO.image}" style="width: 100px ; height: 100px">
                        </td>
                        <td>${DTO.price}</td>
                        <td>
                <form action="UpdateCart" method="POST">
                        <input id="button" type="submit" value="Moreee">
                        <input type="hidden" value="${DTO.name}" name="txtcarName"> 
                </form>
                    <p>${DTO.error}</p>

                        </td>
                <td>
                    <form action="RemoveCart" method="POST">
                        <input id="demo" type="submit" value="Remove" onclick="return myFunction()">
                        <input type="hidden" value="${DTO.carID}" name="txtcarID"> 
                    </form>
                </td>
            </tr>
        </c:forEach>
        <th>Total</th>
        <td></td>
        <td>${sessionScope.total}</td>
        <td> 
            </tbody>
    </table>
</c:if>

       
       
<c:if test="${not empty sessionScope.shCart.cart}">
    <div id="form_b"> 
    <form action="Discount">
        <p>Check Discount Code</p>
        <input type="text" name="txtDicountCode" value="${sessionScope.CodeID}" />
        <input type="hidden" name="Price" value="${sessionScope.total}" />
        <input type="submit" value="Discount" />
        <p>Total price after use discount :${sessionScope.newtotal}</p>
        
        <p style="color: red" >${requestScope.Invalid}</p>
    </form>             
</div>
    <div id="form">
        <h3 id="box_title">Your InFormation I will send You my Car</h3>
        <form action="ConfirmCart" method="POST">
            Name: <br/>
            <div id="text_field">
                <input id="name" type="text" value="${sessionScope.username}" name="txtName" > <br/>
                <p style="color: red" >${requestScope.name}</p>
            </div>
            Phone: <br/>
            <div id="text_field">
                <input id="phone" type="text" value="${sessionScope.userphone}" name="txtPhone"> <br/>
                <p style="color: red" >${requestScope.phone}</p>
            </div>
            Address: <br/>
            <div id="text_field">
                <input id="address" type="text" value="${sessionScope.useraddress}" name="txtAddress">
                <p style="color: red" >${requestScope.address}</p>
            </div>
            <p style="color: red" >${requestScope.Year}</p>
            <p style="color: red" >${requestScope.Month}</p>
            <p style="color: red" >${requestScope.Day}</p>
            <h3>---Rent Date: <input id="x" type="date" name="txtRentDate"  value="${sessionScope.rentalDate}" min="${sessionScope.currentDate}"style="width: 150px"/></h3>
            <p style="color: red" >${requestScope.RentDateF}</p>
            <p style="color: red" >${requestScope.Rent}</p>
            <h3>Return Date: <input id="y" type="date" name="txtPayDate" value="${sessionScope.returnDate}" min="${sessionScope.currentDate}" style="width: 150px"/></h3>
            <p style="color: red" >${requestScope.Pay}</p>
            <input type="hidden" name="txtDicountCode" value="${sessionScope.CodeID}" />
            <input type="hidden" name="Price" value="${sessionScope.total}" />
            <div id="button">
                <input type="submit" value="Total Price" name="btAction">

                <input type="submit" value="Confirm" name="btAction">
            </div>
        </form>
            
    </div>
</c:if>
<c:if test="${empty sessionScope.shCart.cart}">
    <h2 id="empty">Booking Car nowwww !!!!</h2>
</c:if>
-->
<a href="Page">Back to home Page</a>
</html>