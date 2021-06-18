<%-- 
    Document   : home
    Created on : Feb 28, 2021, 2:28:57 PM
    Author     : MSI-PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="home.css">


        <title>Home Page</title>
    </head>
    <body>
        <style>

      
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: darkred;
    border-radius: 5px;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}
#b{
    display: block;
    color: white;
    float: right;
    text-decoration: none;
    
}
#c{
    width: 30px;
    padding: 50%;
}
#pagenumber {
    border: 1px solid black;
    margin-top: 100px;
    margin-left: 850px;
    background-color: floralwhite;
    grid-gap: 15px;
    border-radius: 10px;
    display: grid;
    padding: 20px;
    width: 80px;
    height: 20px;
    grid-template-columns: 10px 10px 10px 10px 10px 10px;
}
li a:hover {
    background-color: #111;

}
body{
    background-image: url(blue-3d-modern-background-design_53876-87397.jpg);
        background-color: darksalmon;


}
.bodyy{
    background-size: auto;
    background-color: darksalmon;
    border: background ;
    padding-top: 20px;
    margin-top: 20px;
    background-image: url(https://www.google.com.vn/url?sa=i&url=https%3A%2F%2Fwww.freepik.com%2Ffree-vector%2Fblue-3d-modern-background-design_4062947.htm&psig=AOvVaw1JcjI2wSaIDRqL2iS6GlIa&ust=1610604704746000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPiw2qygmO4CFQAAAAAdAAAAABAD);


}
.header-box{
    grid-gap: 20px;
    display: grid;
    width: 1700px;
    height: 300px;
    grid-template-columns:300px 1400px ;
    padding: 10px;
    margin: auto;

}
/*hana*/
.headera { 
    width: auto;
    margin-top: 20px;
    padding-left: 200px;
    border-radius: 10px;
    background-image: url(a.jpg);
    background-repeat: no-repeat;
    background-size: cover;
}
.headera > #a1{
    text-align: center;
    color: floralwhite;
    text-decoration: background;
    padding-top: 20px;
}
/*buuton search*/
.header-search{
    padding: 5px;
    margin-top: 20px;
    border-radius: 10px;
    background-image: url(abcd.png);
    background-size: cover;
    background-repeat: no-repeat;
}
.update{
    border-radius: 10px;
    border: solid;
    background-color: appworkspace;
    margin: auto;
    width: 350px;
    padding-top: auto;
    padding: 10px;
    background-image: url(edit.jpg);
    color: gold;
    width: 500px;
    height: 500px;
    background-size: cover;
}
.tba{
    
    width: 500px;
    text-align: center;
    padding-left: 700px;
}
.product {
    display: grid;
    grid-gap: 20px;
    grid-template-columns: 200px 200px 200px 200px 200px;
    width: 1100px;
    padding-top: 5px;
    margin-top: 20px;
    padding-left: 20px;
    border-radius: 10px;
    border: buttonshadow;
}

.bodyy > .product
{
    margin: auto;
    border: buttonshadow;

}

.aM{
    background-color: darksalmon;
    border: 1px solid rgba(0, 0, 0, 0.8);
    padding: 20px;
    font-size: 20px;
    text-align: center;
    border-radius: 5px;
    border-color: aqua;

}
.product > div img{
    text-align: center;
    margin: 5px 10px;
    border: window;


}

    #login_form{
                width: 100%;
                max-width: 400px;
                margin: 20px;
                background-color: brown;
                padding: 15px;
                border: 2px;
                border-radius: 10px;
                opacity: 1;
               
            }





        </style>
        <ul>
            <li>
                <a href="Page">Home</a>
            </li>
            
            <li>
                <c:if test="${not empty sessionScope.username}">
                    <a href="LoadHistory">History</a>
                </c:if>
            </li>
            <li>
                <a href="#">RENT CAR</a>
            </li>
           
            
            <li id="b">
                <c:if test="${empty sessionScope.username}">
                    <a href="login.jsp">Login</a>
                    
                    
                </c:if>
                <c:if test="${not empty sessionScope.username}">
                    <a href="Logout">Logout</a>
                </c:if>


            </li>

            <li id="b">
                <c:if test="${not empty sessionScope.username}">
                    <c:if test="${not empty sessionScope.shCart.cart}"> 
                        <a id="b" href="viewcart.jsp"> View cart 
                            (${sessionScope.shCart.cart.values().size()})


                        </c:if>
                    </a>
                </c:if>

            </li>
            <li id="b">
                <a>Hello ${sessionScope.username}!</a>
            </li>
            <li id="b">
                <a> Phone: 0898540027 </a>
            </li>
            <li id="b">
                <a> Contact: Datdam2407@gmail.com</a>
            </li>


        </ul>
                     <div id="login_form">

         <form action="SearchDate" method="POST">
                    <br/>
                    <font color="Yellow">Car Name</font>: <input type="text" value="${sessionScope.name}" name="txtName"  style="width: 100px"/> 
                    <font color="Yellow">Category:<font> <select name="txtCategory" size="1" style="width: 70px">
                        <c:forEach items="${requestScope.categoryList}" var="d">
                            <option>${d.categoryID}</option>       
                        </c:forEach>
                    </select><br/>
                    <input type="hidden" value="1" name="PageIndex"/>
             Rent Date: <input type="date" name="txtRentDate" value="${sessionScope.txtRentDate}" min="${sessionScope.currentDate}" style="width: 150px"/><br/>
             -Pay Date: <input type="date" name="txtPayDate" value="${sessionScope.txtPayDate}" min="${sessionScope.currentDate}" style="width: 150px"/><br/>
                             <c:if test="${not empty sessionScope.category && empty sessionScope.name}">
                    You are searching Car by ${sessionScope.category}

                </c:if>
            <input type="submit" value="Search" name="btaction"/>
            
        </form>
              <p style="color: red" >${requestScope.Year}</p>
                        <p style="color: red" >${requestScope.Month}</p>
                        <p style="color: red" >${requestScope.Day}</p>
                        <p style="color: red" >${requestScope.RentDateF}</p>
                        <p style="color: red" >${requestScope.Rent}</p>
                        <p style="color: red" >${requestScope.Pay}</p>
         
    </div>
    

    <div class="bodyy">

        <c:if test="${not empty requestScope.listPro && empty requestScope.searchPro}">
            <div class="product">
                <c:forEach items="${requestScope.listPro}" var="p">
                    <div>
                        <div class="aM">
                            <img src="${p.image}" style="width: 100px; height: 100px"/>

                            <h4>${p.name}</h4>                
                            <h5>${p.price}₤</h5>
                            
                            <c:if test="${not empty sessionScope.username}">
                                
                                <form action="AddCart" method="POST">
                                    <input type="hidden" name="txtCarID" value="${p.carID}" />
                                    <input type="hidden" name="txtRentDate" value="${p.rentDate}" />
                                    <input type="hidden" name="txtPayDate" value="${p.payDate}" />
                                    <input type="submit" value="Book Now" name="btaction" />                               
                                    <input type="hidden" name="PageIndex" value="${requestScope.PageIndex}" />
<input type="hidden" name="txtName" value="${sessionScope.name}"/>
<input type="hidden" name="txtCategory" value="${sessionScope.category}"/>
                            </c:if>

                                </form>
                         
                            <h5></h5>

                        </div>
                    </div>
                </c:forEach>
            </div> 
            <div id="pagenumber">     
                <!-- i là bước nhảy-->
                <c:forEach begin="1" end="${end1}" var="i"> 
                    <a href="Page?PageIndex=${i}"> ${i} </a>
                </c:forEach>
            </div>
        </c:if>

        <div class="bodyy">

                
            <c:if test="${not empty requestScope.searchPro}">

                <div class="product">
                <c:forEach items="${requestScope.searchPro}" var="Searchp">
                                             <div class="aM">
                        <img src="${Searchp.image}" style="width: 100px; height: 100px"/>
                        <h4>${Searchp.name}</h4>                
                        <h5>${Searchp.price}₤</h5>
                        <c:if test="${not empty sessionScope.username}">

                        <form action="AddCart" method="POST">
                            <input type="hidden" name="txtCarID" value="${Searchp.carID}" />
                            <input type="submit" value="Book Now" name="btaction" />      

                            <input type="hidden" name="txtRentDate" value="${sessionScope.txtRentDate}"/>
                            <input type="hidden" name="txtPayDate" value="${sessionScope.txtPayDate}"/>
                            <input type="hidden" name="PageIndex" value="${requestScope.PageIndex}"/>
                            <input type="hidden" name="Page" value="hihi" />
                            <input type="hidden" name="txtName" value="${sessionScope.name}"/>
                            <input type="hidden" name="txtCategory" value="${sessionScope.category}"/>
                            <input type="hidden" name="txtQuantity" value="${sessionScope.txtQuantity}"/>
                            
                        </form>
                        </c:if>
                    </div>
                </c:forEach>
            </div>

           <div id="pagenumber">
                          
    <c:if test="${empty sessionScope.txtRentDate}">
                      
              
                    <c:forEach begin="1" end="${end}" var="i">
                          <a href="SearchDate?PageIndex=${i}&txtName=${name}&txtCategory=${category}&txtRentDate=${txtRentDate}&txtPayDate=${txtPayDate}&txtQuantity=${txtQuantity}">${i}</a>
                    </c:forEach>
                </c:if>
                                                                                                             
                        <c:if test="${not empty sessionScope.txtRentDate}">

>
                    <c:forEach begin="1" end="${end}" var="i">
                          <a href="SearchDate?PageIndex=${i}&txtName=${name}&txtCategory=${category}&txtRentDate=${txtRentDate}&txtPayDate=${txtPayDate}&txtQuantity=${txtQuantity}">${i}</a>
                    </c:forEach>
                </c:if>
            </c:if>

            


        </div>

    </div>
</body>
</html>

