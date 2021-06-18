<%-- 
    Document   : feedback
    Created on : Mar 12, 2021, 11:03:08 AM
    Author     : MSI-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Page</title>
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
                max-width: 1000px;
                margin: 20px auto;
                background-color: cornsilk;
                padding: 15px;
                border: 2px;
                border-radius: 10px;
                opacity: 0.7;
            }
              #pic
            {
                width: 1000px;
                height: 600px;
            }
         
        </style>
        <script>
            function myFunction() {
                var txt;
                var r = confirm("Now you want to confirm FeedBack!!");
                if (r == true) {
                    return true;
                } else {
                    return false;
                }
                document.getElementById("demo").innerHTML = txt;
            }
        </script>
        </script>
        <div id="login_form">
            <form action="FeedBack" method="POST" onsubmit="return myFunction()" >
                    <h1>How do you Feel!!!</h1>
                    <p>${param.Name}</p>
                <img id="pic" src="${param.Pic}"/> <br/>

            Comment your feeling<input type="text" name="txtFeedbackContent" value="" /><br/>
            How Star !!<input type="number" name="txtrateStar" value="1" min="1" step="1" max="10"/>
            <input id="demo" type="submit" value="Send FeedBack!!!" name="btaction"/>
            <input type="hidden" value="${param.ID}" name="txtID"/>

    </form>
        </div>
</body>



</html>
