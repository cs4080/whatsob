<%-- 
    Document   : newjsp
    Created on : 21-nov-2015, 3:51:05
    Author     : carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>
            WhatsApp Web LogIn
        </title>     
        <link type="text/css" rel="stylesheet" href="styleChat.css" />
    </head>
    <body style="margin:50px; padding:200px">
        <form id="formulario" action="login.do" method="post" > 

            <input type="text" name="username" placeholder="Name" class="enterText" required><br>
            <p></p>
            <input type="password" name="pwd" placeholder="Password" class="enterText" required><br>
            <p></p>
            <div>
                <input id="entrar" type="submit" name="Enter" class="submit" value="Log in" title="Log in">
            </div>
        </form>
        
    <a href="signin.jsp">Sign in</a>
    </body>
</html>