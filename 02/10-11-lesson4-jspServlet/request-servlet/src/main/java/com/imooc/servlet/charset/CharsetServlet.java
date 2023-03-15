package com.imooc.servlet.charset;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CharsetServlet", value = "/charset/process")
public class CharsetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 对于Tomcat 8.x的版本，默认get请求发送中文就是UTF-8的格式，因此无需转换
        request.setCharacterEncoding("utf-8");
        String ename = request.getParameter("ename");
        String address = request.getParameter("address");
//        String utf8Ename = new  String(ename.getBytes("iso-8859-1"),"utf-8");
        System.out.println(ename+":"+address);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.setCharacterEncoding 方法用于请求体中的字符集转换为UTF-8
        request.setCharacterEncoding("utf-8");
        String ename = request.getParameter("ename");
        String address = request.getParameter("address");
//        String utf8Ename = new  String(ename.getBytes("iso-8859-1"),"utf-8");
        System.out.println(ename+":"+address);
    }
}
