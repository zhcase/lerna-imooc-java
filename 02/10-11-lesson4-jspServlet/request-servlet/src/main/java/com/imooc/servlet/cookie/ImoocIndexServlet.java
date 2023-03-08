package com.imooc.servlet.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie/index")
public class ImoocIndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//        request.getCookies() 用户获取所有cookies
       Cookie[] cs= req.getCookies();
       String user=null;
       if(cs==null){
           resp.getWriter().println("user is not login");
           return;
       }
       for (Cookie c: cs){
           System.out.println(c.getName()+":"+c.getValue());
           if(c.getName().equals("user")){
               user=c.getValue();
               break;
           }
       }
        System.out.println(user);
       if(user==null){
           resp.getWriter().println("user is not login");
       }else{
           resp.getWriter().println(user+" user is login");

       }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
