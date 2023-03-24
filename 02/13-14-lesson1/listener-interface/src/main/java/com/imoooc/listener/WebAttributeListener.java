package com.imoooc.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

public class WebAttributeListener implements ServletContextAttributeListener, HttpSessionAttributeListener, ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
//        ServletContextAttributeListener.super.attributeAdded(event);
        event.getName();
        System.out.println("ServletContext新增属性: e"+event.getName()+"->"+event.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
//        ServletContextAttributeListener.super.attributeRemoved(event);
        System.out.println("attribute移除属性: e"+event.getName()+"->"+event.getValue());

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
//        ServletContextAttributeListener.super.attributeReplaced(event);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
//        HttpSessionAttributeListener.super.attributeAdded(event);
        System.out.println("HttpSession新增属性: e"+event.getName()+"->"+event.getValue());

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
//        HttpSessionAttributeListener.super.attributeRemoved(event);

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
//        HttpSessionAttributeListener.super.attributeReplaced(event);
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
//        ServletRequestAttributeListener.super.attributeAdded(srae);
        System.out.println("Servlet新增属性: e"+srae.getName()+"->"+srae.getValue());

    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
//        ServletRequestAttributeListener.super.attributeRemoved(srae);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
//        ServletRequestAttributeListener.super.attributeReplaced(srae);
    }
}
