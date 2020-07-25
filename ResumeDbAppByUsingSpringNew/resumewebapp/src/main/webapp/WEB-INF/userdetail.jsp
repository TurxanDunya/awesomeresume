<%-- 
    Document   : user
    Created on : Jun 9, 2020, 2:55:24 PM
    Author     : TD-PC
--%>

<%@page import="com.company.entity.User"%>
<%@page import="com.company.main.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User u = (User) request.getAttribute("user");
        %>

        <div>
            <form action="userdetail" method="POST">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="id" value="<%=u.getId()%>"/>
            <label for="name">Name:</label>
            <input type="text" name="name" value="<%=u.getName()%>"/>
            <br/>
            <label for="surname">Surname:</label>
            <input type="text" name="surname" value="<%=u.getSurname()%>"/>
            <br/>
            <label for="email">E-mail:</label>
            <input type="text" name="email" value="<%=u.getEmail()%>"/>
            <br/>
            <label for="phone">Phone:</label>
            <input type="text" name="phone" value="<%=u.getPhone()%>"/>
            <input type="submit" name="save" value="Save">
            </form>
        </div>

    </body>
</html>
