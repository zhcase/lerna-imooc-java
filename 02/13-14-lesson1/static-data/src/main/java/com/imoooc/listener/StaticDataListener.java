package com.imoooc.listener;

import com.imoooc.listener.entity.Channel;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.ArrayList;
import java.util.List;

public class StaticDataListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ServletContextListener.super.contextInitialized(sce);
       List list=new ArrayList();
       list.add(new Channel("免费课程","http://www.baidu.com"));
        list.add(new Channel("实战课程","http://www.taobao.com"));
        list.add(new Channel("就业班","http://www.jd.com"));
        System.out.println(list);
        sce.getServletContext().setAttribute("channelList",list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContextListener.super.contextDestroyed(sce);
    }
}
