package com.imooc.servlet.ServletContext;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "servletContextInit", value = "/servletcontext/init")
public class servletContextInit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        String coyeRight = context.getInitParameter("copyright");
        context.setAttribute("copyright",coyeRight);
        String title = context.getInitParameter("title");

        context.setAttribute("title",title);
        response.getWriter().println("init success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
