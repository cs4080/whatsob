<%-- 
    Document   : mainweb
    Created on : 22-nov-2015, 3:49:12
    Author     : carlos
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" rel="stylesheet" href="styleChat.css" />
    </head>
    <body>
        
        
        <div class="logout">  
            <form method=post id="logout" name="logout" action="logout.do">
                <input type="submit" class="logout" name="userlogout" value="Log out" title="Invalidate the session" >
            </form>
        </div>
        
        <div id="title">
            <% if(session.getAttribute("userChat")!=null){
                out.println("<center><h1>Welcome, "+session.getAttribute("user")+"</h1>"+ "you are chatting with " + session.getAttribute("userChat") + "</center>"); 
            }else{
                 out.println("<center><h1>Welcome, "+session.getAttribute("user") + "</h1></center>");
            }
            
            %> 
        </div>

        <div id="wrapperUser">
            <div id="friend">
                <select name="friendListSelect" class="friendList" multiple>
                <% if(session.getAttribute("friend")!=null){
                    String friends = session.getAttribute("friend").toString();
                    String[] sepFriends = friends.split("/");
                    for (int i = 0; i < sepFriends.length; i++) {
                        
                        out.println("<option value="+i+" >"+sepFriends [i]+"</option>");
                    }
                    

                }%>
                </select>
            </div>
            <form method=post id="user" name="user" action="">
                <input name="adduser" type="text" id="adduser" size="63" placeholder="User name" required/>                          
                <input type="hidden" name="userloged" value="<%session.getAttribute("user"); session.getAttribute("userChat");%>" />         
                
                <input name="submitmsg" type="submit"  id="addfriend" class="submit" value="Add user" title="Add a new user to your friend list" onclick="addFriend();"/>
                <input name="submitmsg" type="submit"  id="removefriend" class="submit" value="Remove user" title="Remove user from your friend list" onclick="deleteFriend();"/>
                <input name="submitmsg" type="submit"  id="selectuser" class="submit" value="Select user" title="Select the user you want to chat" onclick="selectUser();"/>

            </form>
        </div>

        <div id="wrapperChat">
            <div id="chatbox">
                <% if(session.getAttribute("message")!=null){
                    String messages = session.getAttribute("message").toString();
                    String[] sepMessages = messages.replace("null", "").split("/");
                     
                    for (int i = 0; i < sepMessages.length; i++) {
                        out.println("<h4>"+sepMessages[i]+"</h4>");
                    }

                }%>
               
            </div>   
		
            <form method=post name="message" action="send.do">
                <input type="hidden" name="userloged" value="<%session.getAttribute("user"); session.getAttribute("userChat");%>" />
                <input name="usermsg" type="text" id="usermsg" size="63" placeholder="Message"/>
                <input name="submitmsg" type="submit"  id="send" class="submit" align="right" value="Send" title="Send message"/>             
            </form>
            
            <form method=post name="message" action="refresh.do">
                <input type="hidden" name="userloged" value="<%session.getAttribute("user"); session.getAttribute("userChat");%>" />
                <input name="submitmsg" type="submit"  id="send" class="submit" align="right" value="REFRESH CHAT" title="Refresh the chat"/>             
            </form>
            
        </div>
   
    </body>
        <script type="text/javascript">
        function addFriend() {
            document.getElementById("user").action = "adduser.do";
        } 
        
        function deleteFriend() {
            document.getElementById("user").action = "removeuser.do";
        } 
        
        function selectUser() {
            document.getElementById("user").action = "selectuser.do";
        } 
        
    </script>
</html>
