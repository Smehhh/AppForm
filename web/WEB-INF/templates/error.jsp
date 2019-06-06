<%-- 
    Document   : error
    Created on : 31.10.2018, 21:49:03
    Author     : BURNERON
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= request.getAttribute("error") %></h1>
    </body>
</html>
