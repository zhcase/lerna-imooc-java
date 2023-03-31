package com.imoooc.spring.ioc;

import com.imoooc.spring.ioc.entity.Dog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
       Dog  dog= context.getBean("Dog", Dog.class);
        System.out.println(dog.getUsername());
    }
}
