package com.imooc.servlet.session;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SessionLoginServlet", value = "/session/login")
public class SessionLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("用户登录成功");
        HttpSession session = request.getSession();
        session.setAttribute("name","张三");
        request.getRequestDispatcher("/session/index").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
