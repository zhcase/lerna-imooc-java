package com.imooc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // 接收请求发来的参数
        String name=req.getParameter("name");
        String html="<h1 style='color:red'>hi,"+name+"！</h1><hr/>";
        System.out.println("返回给浏览器的响应数据为:"+html);
        PrintWriter out = resp.getWriter();
        out.println(html); // 将html 发送给浏览器

    }
}
