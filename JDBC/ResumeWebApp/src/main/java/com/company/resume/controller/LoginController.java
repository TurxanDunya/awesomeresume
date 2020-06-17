package com.company.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;
import com.company.resume.util.ControllerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"}) //url-i teyin edirik
public class LoginController extends HttpServlet {

     private UserDaoInter userDao = Context.instanceUserDao(); //Dao - data access object
     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         request.getRequestDispatcher("login.jsp").forward(request, response);
     }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         try {
             String email = request.getParameter("email");
             String password = request.getParameter("password");
             User user = userDao.findByEmailAndPassword(email, password);

             if (user == null) {
                 throw new IllegalArgumentException("Email or password is incorrect!!!");
             }

             request.getSession().setAttribute("loggedInUser", user);
             response.sendRedirect("users");
         } catch (Exception ex){
             ControllerUtil.errorPage(response, ex);
         }
    }
}