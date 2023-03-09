package com.imooc.servlet.pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PatternServlet  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 查询员工的基本信息
        // 获取当前访问的url

        String url=req.getRequestURI().toString();
        System.out.println(url);
        String id = url.substring(url.lastIndexOf("/")+1);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("id is "+id);
        if(id.equals("1")){
            System.out.println("张三");
        }
//        super.service(req, resp);
    }
}
