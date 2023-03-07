package com.imooc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SampleServlet extends HttpServlet {

//    service 是请求处理的核心方法，无论是get或者post都会被service()方法处理
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodsName=req.getMethod();
           PrintWriter out = resp.getWriter(); // 向浏览器输出的数据流
//           out.println("<a href='http://www.baidu.com'>baidu</a>");
//             Integer n= Integer.parseInt(req.getParameter("n"));
//             Integer sum=0;
//             for(int i=0; i<=n;n--){
//                 sum+=n;
//             }
//           PrintWriter out = resp.getWriter();
//        out.println("<h1>"+sum+"</h1>");
         String name=req.getParameter("name");
        String mobile=req.getParameter("mobile");
        String sex=req.getParameter("sex");

        String[] specs=req.getParameterValues("spec");

        out.println("<h1>"+name+"</h1>");
        out.println("<h1>"+mobile+"</h1>");
        out.println("<h1>"+sex+"</h1>");
        for(int i=0;i<specs.length;i++){
            out.println("<h1>"+specs[i]+"</h1>");

        }
        out.println(methodsName);

    }
}
