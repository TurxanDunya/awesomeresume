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
    <script type="text/javascript" src="assets/js/users.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
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
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input onkeyup="writeWhatIamTyping()"
                           placeholder="Enter Name" class="form-group" type="text" name="name" value=""/>
                </div>
                <div class="form-group">
                    <label for="surname">Surname:</label>
                    <input placeholder="Enter Surname" class="form-group" type="text" name="surname" value=""/>
                </div>

                <input visible="true" type="submit" class="btn btn-primary" name="search" value="Search"
                       id="btnsearch"/> //submit formdaki prosesi ishe salir
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
                    <td><%=u.getName()%>
                    </td>
                    <td><%=u.getSurname()%>
                    </td>
                    <td><%=u.getNationality().getName() == null ? "N/A" : u.getNationality().getName()%>
                    </td>
                    <td style="width: 5px">
                        <input type="hidden" name="id" value="<%=u.getId()%>"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button class="btn btn-danger" type="submit" value="delete"
                                data-toggle="modal" data-target="#exampleModal"
                                onclick="setIdForDelete(<%=u.getId()%>)">
                            <i class="fas fa-trash-alt"></i>
                        </button>
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

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary">
        Launch demo modal
    </button>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Are you sure?
                </div>
                <div class="modal-footer">
                    <form action="userdetail" method="POST">
                        <input type="hidden" name="id" value="" id="idForDelete"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="submit" class="btn btn-danger" value="Delete"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>