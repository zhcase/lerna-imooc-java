package com.imoooc.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class WebListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ServletContextListener.super.contextInitialized(sce);
        System.out.println("ServletContext已初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContextListener.super.contextDestroyed(sce);
        System.out.println("ServletContext已被销毁");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
//        HttpSessionListener.super.sessionCreated(se);
        HttpSession session = se.getSession();
        System.out.println("Session已被创建, SessionId："+session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
//        HttpSessionListener.super.sessionDestroyed(se);
        System.out.println("session已被销毁");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
//        ServletRequestListener.super.requestDestroyed(sre);
        System.out.println("HttpServletRequest已被销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
       HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
//        ServletRequestListener.super.requestInitialized(sre);
        System.out.println("HttpServletRequest已被创建，URL:"+request.getRequestURI());
    }
}
