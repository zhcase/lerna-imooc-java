package com.imoooc.spring.ioc.entity;

public class Cat {


    private String username;
    private Integer age;

    public Cat(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public Cat() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
