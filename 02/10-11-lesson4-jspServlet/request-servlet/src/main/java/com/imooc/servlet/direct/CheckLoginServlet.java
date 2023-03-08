package com.imooc.servlet.direct;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/direct/check")
public class CheckLoginServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        String username= req.getParameter("username");

//        System.out.println(req);
//        System.out.println("用户登录成功");
        req.setAttribute("username",username);
//        // 实现的请求转发的功能
         req.getRequestDispatcher("/direct/index").forward(req,resp);
        //响应重定向需要增加contextPath
//        resp.sendRedirect("/request_servlet_war_exploded/direct/index");
//               resp.sendRedirect("http://www.baidu.com");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
