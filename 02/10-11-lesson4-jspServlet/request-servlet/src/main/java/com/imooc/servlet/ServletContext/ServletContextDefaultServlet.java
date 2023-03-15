package com.imooc.servlet.ServletContext;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletContextDefaultServlet", value = "/servletcontext/default")
public class ServletContextDefaultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         ServletContext context = request.getServletContext();
         String copyright =(String) context.getAttribute("copyright");
         String title = (String) context.getAttribute("title");
         response.setContentType("text/html;charset=utf-8");
         response.getWriter().println("<h1>"+title+"</h1>"+copyright);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
