package com.imooc.spring.ioc;

import com.imooc.spring.ioc.entity.Apple;
import com.imooc.spring.ioc.entity.Child;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
//        创建Spring Ioc容器 并根据配置文件在容器中实例化对象
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Apple sweetApple = context.getBean("sweetApple", Apple.class);
        System.out.println(sweetApple.getTitle());
        Child lily= context.getBean("lily", Child.class);
        lily.eat();
        Child andi= context.getBean("andy", Child.class);
        andi.eat();
        Child luna= context.getBean("luna", Child.class);
        luna.eat();

    }
}
