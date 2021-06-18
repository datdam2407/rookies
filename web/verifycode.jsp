<%-- 
    Document   : verifycode
    Created on : Mar 13, 2021, 6:39:02 PM
    Author     : MSI-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Code Page</title>
    </head>
    <body>
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
                background-color: cornsilk;
                padding: 15px;
                border: 2px;
                border-radius: 10px;
                opacity: 0.7;
            }
         
        </style>
        <div id="login_form">
            <span> <p>We already send a verification code to your email.</p></span>
        
        <form action="VerifyCode" method="post">
            -->Type Here  <input type="text" name="authcode" >
          <input type="submit" value="verify">
        </form>
        </div>
    </body>
</html>
