package com.imooc.spring.ioc.bookshop.service;

import com.imooc.spring.ioc.bookshop.dao.BookDao;

public class BookService {
    private BookDao bookDao;
    public  void purchase(){
        System.out.println("正在执行图书采购业务方法");
        bookDao.insert();
    }
}
