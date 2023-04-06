package com.imooc.mall.service.impl;

import com.imooc.mall.exception.ImoocMailExceptionEnum;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.dao.ProductMapper;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.request.AddProductRequest;
import com.imooc.mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public void add(AddProductRequest addProductRequest) throws ImoocMallException {
        Product product = new Product();
        BeanUtils.copyProperties(addProductRequest, product);
        Product productOld = productMapper.selectByName(addProductRequest.getName());
        if (productOld != null) {
            throw new ImoocMallException(ImoocMailExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.insertSelective(product);
        if (count == 0) {
            throw new ImoocMallException(ImoocMailExceptionEnum.CREATE_FAILED);
        }
    }

    @Override
    public void update(Product updateProduct) throws ImoocMallException {
        Product productOld = productMapper.selectByName(updateProduct.getName());
        // 同名且不同id 不能继续修改
        if (productOld != null && !productOld.getId().equals(updateProduct.getId())) {
            throw new ImoocMallException(ImoocMailExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.updateByPrimaryKeySelective(updateProduct);
        if(count==0){
            throw new ImoocMallException(ImoocMailExceptionEnum.UPDATE_FAILED);

        }
    }
    @Override
    public void delete(Integer id) throws ImoocMallException {
        Product productOld = productMapper.selectByPrimaryKey(id);
        // 同查不到该记录 无法删除
        if (productOld == null ) {
            throw new ImoocMallException(ImoocMailExceptionEnum.DELETE_FAILD);
        }
        int count = productMapper.deleteByPrimaryKey(id);
        if(count==0){
            throw new ImoocMallException(ImoocMailExceptionEnum.DELETE_FAILD);
        }
    }
}
