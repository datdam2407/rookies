<%-- 
    Document   : verify
    Created on : Mar 5, 2021, 8:21:43 PM
    Author     : MSI-PC
--%>

  <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Page</title>
    </head>
    <body>
<!--        <span>We already send a verification  code to your email.</span>
        
        <form action="VerifyCode" method="post">
            <input type="text" name="authcode" >
            <input type="submit" value="verify">
        </form>-->
         <style>
            body{
                background-color: darksalmon;
                background-size: cover;
                background-repeat: no-repeat;
            }

            #login_form{
                width: 100%;
                max-width: 400px;
                margin: 20px auto;
                background-color: antiquewhite;
                padding: 15px;
                border: 2px;
                border-radius: 10px;
                opacity: 0.7;
            }

            #box_title{
                font-family: Georgia, 'Times New Roman', Times, serif;
                color: #c1dad6;
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
                margin-top: 10px;
            }

            #button{
                text-align: right;
                margin-top: 10px;
            }

            #button input{
                padding: 7.5px 15px;
                border-radius: 5px;
                background-color: #c1dad6;
                color: #6d929b;
                border: none;
                outline: none;
            }

            #login{
                text-decoration: none;
                font-family: Georgia, 'Times New Roman', Times, serif;
                color: #6d929b;
                padding: 1px 15px;
                background-color: #e8d0a9;
                border-radius: 2px;
                border: 2px dotted #6d929b;
                text-align: center; 
            }
        </style>
        
         <div id="login_form">
            <h1 id="box_title">Would you mind Verifying one more step!!!</h1>
            <p color="red">${requestScope.error}</p>
            <form action="userVerify" method="POST">
                <label>User Email:</label><br/>
                <div id="text_field">
                    <input id="email" type="email" name="useremail" value="${sessionScope.username}" readonly=""><br/>
                </div>
                <div id="button">
                    <input id="button" type="submit" value="Send verify code">
                </div>
            </form>
            <a id="login" href="login.jsp">Back to login page</a>
        </div>

        
        
        
    </body>
</html>
