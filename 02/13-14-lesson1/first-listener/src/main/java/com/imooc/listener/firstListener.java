package com.imooc.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class firstListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

//        ServletContextListener.super.contextInitialized(sce);
        System.out.println("ServletContext已初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContextListener.super.contextDestroyed(sce);\
        System.out.println("ServletContext已销毁");

    }
}
