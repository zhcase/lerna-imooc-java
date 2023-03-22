package com.imoooc.listener;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HelloServlet", value = "/HelloServlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("hello world");
        request.getServletContext().setAttribute("sc-attr1","sc-attr-value1");
        request.getServletContext().removeAttribute("sc-attr1");
        request.getSession().setAttribute("session-attr1","session-attr1");
        request.setAttribute("request-attr1","request-attr-value");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
