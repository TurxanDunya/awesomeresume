<%-- 
    Document   : user
    Created on : Jun 9, 2020, 2:55:24 PM
    Author     : TD-PC
--%>

<%@page import="com.company.entity.User" %>
<%@page import="com.company.main.Context" %>
<%@page import="com.company.dao.inter.UserDaoInter" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="assets/css/users.css">
    <script src="https://kit.fontawesome.com/9986b78616.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script>
        function writeWhatIamTyping(){
            var input = document.getElementById("WhatIamTyping"); //her bir elemente id-ler teyin edirik. getElementByid hemin id ye gore cagirir
            var text = document.getElementById("typing");
            var inputStr = input.value;
            text.innerHTML = inputStr;  //inner html, spanlarin arasidir
        }

        function changeColor() {
            var btnsearch = document.getElementById("btnsearch");
            btnsearch.style = "background-color:red";
        }
    </script>
</head>
<body>
    <%
    UserDaoInter userDao = Context.instanceUserDao();

    String name = request.getParameter("name");
    String surname = request.getParameter("surname");
    String nationalityIdStr = request.getParameter("nid");
    Integer nationalityId = null;
    if (nationalityIdStr != null && !nationalityIdStr.trim().isEmpty()) {
        nationalityId = Integer.parseInt(nationalityIdStr);
    }

    List<User> list = userDao.getAll(name, surname, nationalityId);
    %>

<div class="container mycontainer col-12">
        <div class="row">
            <div class="col-4">
            <form action="users.jsp" method="GET">
            </div>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input onkeyup="writeWhatIamTyping()"
                            placeholder="Enter Name" class="form-group" type="text" name="name" value=""
                    id="whatIamtyping"/>
                    text here:
                    <span id="typing">

                    </span>
                </div>
                <div class="form-group">
                    <label for="surname">Surname:</label>
                    <input placeholder="Enter Surname" class="form-group" type="text" name="surname" value=""/>
                </div>

                <input type="submit" class="btn btn-primary" name="search" value="Search" id="btnsearch">
                <input type="submit" class="btn btn-primary" name="search" value="Search">
            </form>
        </div>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Nationality</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (User u : list) {
                %>
                <tr>
                    <td><%=u.getName()%> </td>
                    <td><%=u.getSurname()%> </td>
                    <td><%=u.getNationality().getName() == null ? "N/A" : u.getNationality().getName()%> </td>
                    <td style="width: 5px">
                        <form action="userdetail" method="POST">
                            <input type="hidden" name="id" value="<%=u.getId()%>"/>
                            <input type="hidden" name="action" value="delete"/>
                        <button class="btn btn-danger" type="submit" value="delete">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                        </form>
                    </td>
                    <td style="width: 5px">
                        <form action="userdetail" method="GET">
                            <input type="hidden" name="id" value="<%=u.getId()%>"/>
                            <input type="hidden" name="id" value="update"/>
                            <button class="btn btn-secondary" type="submit" value="update">
                                <i class="fas fa-pencil-alt"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
</div>

</body>
</html>