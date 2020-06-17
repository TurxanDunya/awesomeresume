package com.company.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserDetailController", urlPatterns = {"/userdetail"}) //bunu xml-le de evez ede bilerik. web.xml-e bax!
public class UserDetailController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        String action = request.getParameter("action");
        if(action.equals("update")){

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        System.out.println("Name:" + name);
        System.out.println("Surname" + surname);
        
        User user = userDao.getById(6);
        user.setName(name);
        user.setSurname(surname);

        userDao.updateUser(user);
        } else if(action.equals("delete")){
            userDao.removeUser(id);
        }
        response.sendRedirect("users");
    }

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
         String userIdStr = request.getParameter("id");
         if (userIdStr == null || userIdStr.trim().isEmpty()) {
             throw new IllegalArgumentException("id is not specified");
         }

         Integer userId = Integer.parseInt(userIdStr);

         UserDaoInter userDao = Context.instanceUserDao();
         User u = userDao.getById(userId);
         if(u == null){
             throw new IllegalArgumentException("Oh, sorry: There is no user with this id");
         }

         request.setAttribute("owner", true);
         request.setAttribute("user", u);
         request.getRequestDispatcher("userdetail.jsp").forward(request, response);
         //forward - redirectin eksine olaraq, birbasa hansisa linke direct elemir. Sadece hemin url-e yonlendirib, ozuymus kimi teqdim edir
        }catch(Exception ex){
                ex.printStackTrace();
                response.sendRedirect("error?msg="+ex.getMessage());
            }
     }
}