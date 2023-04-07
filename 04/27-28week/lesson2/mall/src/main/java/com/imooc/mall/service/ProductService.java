package com.imooc.mall.service;


import com.github.pagehelper.PageInfo;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.request.AddProductRequest;
import com.imooc.mall.model.request.ProductListReq;

/**
 * 商品Service
 */
public interface ProductService {

    void  add(AddProductRequest addProductRequest) throws ImoocMallException;

    void update(Product updateProduct) throws ImoocMallException;

    void delete(Integer id) throws ImoocMallException;

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    Product detail(Integer id);

    PageInfo list(ProductListReq productListReq);
}
