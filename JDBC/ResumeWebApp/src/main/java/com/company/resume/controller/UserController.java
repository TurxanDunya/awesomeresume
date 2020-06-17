package com.company.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController", urlPatterns = {"/users"}) //bunu xml-le de evez ede bilerik. web.xml-e bax!
public class UserController extends HttpServlet {

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         request.getRequestDispatcher("users.jsp").forward(request, response);
     }
}