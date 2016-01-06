<%-- 
    Document   : signin
    Created on : 21-nov-2015, 5:01:16
    Author     : carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>
            WhatsApp Web SignIn
        </title>  
        <link type="text/css" rel="stylesheet" href="styleChat.css" />
    </head>
    <body>
        <form id="regisForm" method="post" action="">
            <h1>Registration window</h1>
            <table id="table">
                <tr>
                    <th>Name:</th>
                    <td><input type="text" size="20" name="username" placeholder="Name" class="enterText" required></td>
                </tr>
                <tr>
                    <th>Last:</th>
                    <td><input type="text" size="20" name="userlname" placeholder="Last" class="enterText" required></td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td><input type="text" size="20" name="email" placeholder="Email" class="enterText" required></td>
                </tr>
                <tr>
                    <th>Phone<h25>(optional)</h25>:</th>
                    <td><input type="text" size="20" name="phone" placeholder="Phone" class="enterText"></td>
                </tr>
                <tr>
                    <th>Password:</th>
                    <td><input id="pwd1" type="password" size="20" name="pwd" placeholder="Password" class="enterText" required></td>
                </tr>
                
                    <th>Confirm password:</th>
                    <td>
                        <input id="pwd2" type="password" size="20" name="pwd2" placeholder="Confirm password" class="enterText" onkeyup="checkPwd();" required>
                    </td>

            </table>
            <input id="confirm" type="submit" name="confirmar" class="submit" value="Confirm" title="Create the account">
        </form>
    <a href="index.jsp">Back Home</a>
    </body>
    
    <script type="text/javascript">
        function checkPwd() {
            if (document.getElementById('pwd2').value == document.getElementById('pwd1').value) {
                pwd2.style.backgroundColor = "#00ff00";
                document.getElementById("regisForm").action = "signin.do";
            } else {
                pwd2.style.backgroundColor = "#ff0000";
                document.getElementById("regisForm").action = "#";
            }
        }  
        
    </script>
    
</html>