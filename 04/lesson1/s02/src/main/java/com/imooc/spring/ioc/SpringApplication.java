package com.imooc.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
//        初始化IOC容器实例化
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

    }
}
