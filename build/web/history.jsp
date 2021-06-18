<%-- 
    Document   : history
    Created on : Mar 9, 2021, 10:48:17 AM
    Author     : MSI-PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
        <link rel="stylesheet" href="admincss.css">

    </head>
    <script>
        function myFunction() {
            var txt;
            var r = confirm("Do you want to Delete Order");
            if (r == true) {
                return true;
            } else {
                return false;
            }
            document.getElementById("demo").innerHTML = txt;
        }
    </script>
    <body>
        <style>
            body{
                background-color: bisque;
                background-size: cover;
                background-repeat: no-repeat;
            }
            #order_box
            {
                width: 1700px;
                background-color: darksalmon;
                margin: 20px auto;
                border: 4px solid #e8d0a9;
                border-radius: 5px; 
            }

            #order_box p
            {
                margin-left: 100px;
                color: #d8e6df;
                font-size: 25px;
            }

            #page_table{
                border-radius: 10px;
                margin: 50px auto 0px;
                heigth: auto;
                width: 1680px;
                border: solid;
                background-color:#d8e6df
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

            #name
            {
                margin-top: 20px;
                font-size: 180%;
            }

        </style>
        <h1>History</h1>
        <div id="order_box">
            <form action="History" method="POST">

                Car Name: <input  type="text" name="txtname" value="${sessionScope.name}" placeholder="Type car's name to search" style="width: 250px"/>

                <input type="hidden" name="usser" value="${sessionScope.LOGIN_USER}" />
                <input id="button" type="submit" value="Search history" name="btaction"/> <br/>  <br/>
                <br/>
            </form>
            <form action="SearchDateHistory" method="POST">
                From: <input type="date" name="txtRent" value="${sessionScope.from}" /><br/>
                ---To: <input type="date" name="txtPay" value="${sessionScope.to}" /><br/> <br/>
                <input id="button" type="submit" value="Search OrderDate" name="btaction"/> <br/>
            </form><br/>


        </div>
        <form action="Page" method="POST">
            <input type="submit" value="Back to Home Page" name="btaction"/>
        </form>
    </div>



    <c:if test="${not empty requestScope.listOrder}">

        <c:forEach items="${requestScope.listOrder}" var="Ha">
            <div id="order_box">

                <div>
                    <tr>
                    <p>${Ha.oderID}</p>

                    <p id="name">Name: ${Ha.customerName}</p>
                    <p>Address: ${Ha.phone}</p>
                    <p>Phone: ${Ha.address}</p>
<!--                        <p>Create date: ${Ha.createDate}</p>-->
                    <p>Rental date: ${Ha.date}</p>
                    <p>Return date: ${Ha.returnDate}</p>
                    <p>Total: ${Ha.total} $</p>

                    <form action="UpdateOder" method="POST">
                        <c:if test="${Ha.status eq 'Paid      '}">
                            <input id="demo" type="submit" value="Delete" name="btaction" onclick="return myFunction()"/>
                        </c:if>
                        <input type="hidden" value="${Ha.oderID}" name="txtorderID"/>
                    </form>

                </div>
                <div>   
                    <table id="page_table" border="1">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>Comment</th>
                                <th>Star</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${Ha.ls}" var="His" varStatus="counter">
                                <tr>

                                    <td>${His.carID}</td>
                                    <td>${His.name}</td>
                                    <td>
                                        <img src="${His.image}" style="width: 100px ; height: 100px">
                                    </td>
                                    <td>${His.price}</td>   
                                    <td>
                                        <form action="feedback.jsp" method="POST">

                                            <c:if test="${Ha.status eq 'Paid      '}">
                                                <input type="submit" value="feedback" name="btaction"/>
                                            </c:if>    
                                            <input type="hidden" value="${His.carID}" name="ID"/>
                                            <input type="hidden" value="${His.image}" name="Pic"/>
                                            <input type="hidden" value="${His.name}" name="Name"/>
                                        </form> 
                                        ${His.feedback}

                                    </td>
                                    <td>
                                        <c:if test="${not empty His.feedback or His.rateStar > 0}">

                                            Rating: ${His.rateStar} *
                                        </c:if>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>

            </c:forEach>
        </c:if>

        <c:if test="${empty requestScope.listOrder and not empty requestScope.listSearchOrder}">
            <c:forEach items="${requestScope.listSearchOrder}" var="p">
                <div id="order_box">    
                    <div>
                        <p id="name">Name: ${p.customerName}</p>
                        <p>Address: ${p.phone}</p>
                        <p>Phone: ${p.address}</p>
                        <p>Create date: ${p.createDate}</p>
                        <p>Rental date: ${p.date}</p>
                        <p>Return date: ${p.returnDate}</p>
                        <p>Total: ${p.total} $</p>

                        <form action="UpdateOder" method="POST">
                            <c:if test="${p.status eq 'Paid      '}">
                                <input id="demo" type="submit" value="Delete" name="btaction" onclick="return myFunction()"/>

                            </c:if>
                            <input type="hidden" value="${p.oderID}" name="txtorderID"/>
                        </form>

                    


                    </div>
                    <div>   
                        <table id="page_table" border="1">
                            <thead>
                               <tr>
                                <th>ID</th>

                                <th>Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>Comment</th>
                                <th>Star</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${p.ls}" var="dto">
                                    <tr>
                                          <td>${dto.carID}</td>
                                        <td>${dto.name}</td>
                                        <td>
                                            <img src="${dto.image}" style="width: 100px ; height: 100px">
                                        </td>
                                        <td>${dto.price}</td>
                                        <td>
                                            <form action="feedback.jsp" method="POST">

                                                <c:if test="${p.status eq 'Paid      '}">
                                                    <input type="submit" value="feedback" name="btaction"/>
                                                </c:if>    
                                                <input type="hidden" value="${dto.carID}" name="ID"/>
                                                <input type="hidden" value="${dto.image}" name="Pic"/>
                                                <input type="hidden" value="${dto.name}" name="Name"/>
                                            </form> 
                                            ${dto.feedback}

                                        </td>
                                        <td>
                                            <c:if test="${not empty dto.feedback or dto.rateStar > 0}">

                                                Rating: ${dto.rateStar} *
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>    
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <p style="color: red" >${requestScope.Day}</p>



</body>

</html>

