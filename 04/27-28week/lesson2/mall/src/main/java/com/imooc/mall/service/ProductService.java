package com.imooc.mall.service;


import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.request.AddProductRequest;

/**
 * 商品Service
 */
public interface ProductService {

    void  add(AddProductRequest addProductRequest) throws ImoocMallException;

    void update(Product updateProduct) throws ImoocMallException;

    void delete(Integer id) throws ImoocMallException;
}
