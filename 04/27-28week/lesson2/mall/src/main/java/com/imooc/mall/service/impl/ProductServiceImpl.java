package com.imooc.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMailExceptionEnum;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.dao.ProductMapper;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.query.ProductListQuery;
import com.imooc.mall.model.request.AddProductRequest;
import com.imooc.mall.model.request.ProductListReq;
import com.imooc.mall.model.vo.CategoryVo;
import com.imooc.mall.service.CategoryService;
import com.imooc.mall.service.ProductService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryService categoryService;

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
        if (count == 0) {
            throw new ImoocMallException(ImoocMailExceptionEnum.UPDATE_FAILED);

        }
    }

    @Override
    public void delete(Integer id) throws ImoocMallException {
        Product productOld = productMapper.selectByPrimaryKey(id);
        // 同查不到该记录 无法删除
        if (productOld == null) {
            throw new ImoocMallException(ImoocMailExceptionEnum.DELETE_FAILD);
        }
        int count = productMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new ImoocMallException(ImoocMailExceptionEnum.DELETE_FAILD);
        }
    }

    @Override
    public void batchUpdateSellStatus(Integer[] ids, Integer sellStatus) {
        productMapper.batchUpdateSellStatus(ids, sellStatus);
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.selectListForAdmin();
        PageInfo pageInfo = new PageInfo(products);
        return pageInfo;
    }

    @Override
    public Product detail(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    @Override
    public PageInfo list(ProductListReq productListReq) {
        // 构建query 对象
        ProductListQuery productListQuery = new ProductListQuery();

        // 搜索处理
        if (!StringUtils.isNullOrEmpty(productListReq.getKeyword())) {
            String keyword = "%" + productListReq.getKeyword() + "%";
            productListReq.setKeyword(keyword);
        }
        // 目录处理 ： 如果查某个目录下的商品 不仅是需要查出该目录下的，还要吧所有子目录的商品都查出来 所以要拿到一个目录id 的List
        if (productListReq.getCategoryId() != null) {
            List<CategoryVo> categoryVoList = categoryService.listCategoryForCustomer(productListReq.getCategoryId());
            ArrayList<Integer> categoryIds = new ArrayList<>();
            categoryIds.add(productListReq.getCategoryId());
            getCategoryIds(categoryVoList, categoryIds);
            productListQuery.setCategoryIds(categoryIds);
        }
        // 排序处理
        String orderBy = productListReq.getOrderBy();
        if (Constant.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)) {
            PageHelper.startPage(productListReq.getPageNum(), productListReq.getPageSize(), orderBy);
        } else {
            PageHelper.startPage(productListReq.getPageNum(), productListReq.getPageSize());
        }
        List<Product> productList = productMapper.selectList(productListQuery);
        PageInfo pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }

    private void getCategoryIds(List<CategoryVo> categoryVoList, ArrayList<Integer> categoryIds) {
        for (int i = 0; i < categoryVoList.size(); i++) {
            CategoryVo categoryVo = categoryVoList.get(i);
            if (categoryVo != null) {
                categoryIds.add(categoryVo.getId());
                getCategoryIds(categoryVo.getChildCategory(), categoryIds);
            }
        }
    }
}
