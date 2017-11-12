<%-- 
    Document   : login
    Created on : 12/11/2017, 14:09:22
    Author     : Syane
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login MiniForum</title>
    </head>
    <body>
        <% try{
             String logout = request.getParameter("logout");
            if(logout.equals("logout")){
                session.invalidate();
             }
            }
            catch(Exception e){
            }
           %>
        <form action="index.html">
            Login: <input type="text" name="login">
            <input type="submit" value="entrar">
        </form>
    </body>
</html>
