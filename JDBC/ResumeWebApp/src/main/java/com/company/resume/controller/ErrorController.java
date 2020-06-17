package com.company.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ErrorController", urlPatterns = {"/error"}) //bunu xml-le de evez ede bilerik. web.xml-e bax!
public class ErrorController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         request.getRequestDispatcher("error.jsp").forward(request, response);
        }
     }