package com.imooc.total;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestTotalListener  implements ServletContextListener, ServletRequestListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ServletContextListener.super.contextInitialized(sce);
        List timeList=new ArrayList();
        List valueList=new ArrayList();
        sce.getServletContext().setAttribute("timeList",timeList);
        sce.getServletContext().setAttribute("valueList",valueList);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContextListener.super.contextDestroyed(sce);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
//        ServletRequestListener.super.requestDestroyed(sre);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
//        ServletRequestListener.super.requestInitialized(sre);
         HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
         String url=request.getRequestURI().toString();
         if(url.endsWith("/rt")==true){
             return;
         }
         List<String> timeList = (List)sre.getServletContext().getAttribute("timeList");
         List<Integer> valueList = (List)sre.getServletContext().getAttribute("valueList");
         Date date=new Date();
         SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
         String time = sdf.format(date);
         if(timeList.indexOf(time)==-1){
            timeList.add(time);
            valueList.add(1);
            sre.getServletContext().setAttribute("timeList",timeList);
            sre.getServletContext().setAttribute("valueList",valueList);
         }else{
                int index = timeList.indexOf(time);
                int value = valueList.get(index);
                valueList.set(index,value+1);
                sre.getServletContext().setAttribute("valueList",valueList);
         }
    }
}
