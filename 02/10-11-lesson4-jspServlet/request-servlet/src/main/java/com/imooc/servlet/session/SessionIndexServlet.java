package com.imooc.servlet.session;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SessionIndexServlet", value = "/session/index")
public class SessionIndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
       String sessionId = session.getId();
        System.out.println(sessionId);
       String name = (String)session.getAttribute("name");
       response.setContentType("text/html;charset=utf-8");
       response.getWriter().println("这是首页，当前用户为"+name);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
