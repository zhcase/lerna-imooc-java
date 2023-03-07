package com.imooc.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class RequestMethodsServlet  extends HttpServlet {
            //处理get请求
            public  void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
                    String name=req.getParameter("name");
                    res.getWriter().println("<h1 style='color:green'>"+name+"</h1>");
            }
    //处理post请求
           public  void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
                    String name=req.getParameter("name");
                    res.getWriter().println("<h1 style='color:red'>"+name+"</h1>");
    }

}
