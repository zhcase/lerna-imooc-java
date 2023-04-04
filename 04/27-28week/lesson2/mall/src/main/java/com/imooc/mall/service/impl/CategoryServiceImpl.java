package com.imooc.mall.service.impl;

import com.imooc.mall.exception.ImoocMailExceptionEnum;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.dao.CategoryMapper;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 目录分类实现类 Service实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryReq addCategoryReq) throws ImoocMallException {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryReq, category);
        Category categoryOld = categoryMapper.selectByName(addCategoryReq.getName());
        if (categoryOld != null) {
            throw new ImoocMallException(ImoocMailExceptionEnum.NAME_EXISTED);
        }
        int count = categoryMapper.insertSelective(category);
        if (count == 0) {
            throw new ImoocMallException(ImoocMailExceptionEnum.CREATE_FAILED);
        }

    }

    @Override
    public void update(Category updateCategory) throws ImoocMallException {
        if(updateCategory.getName()!=null){
          Category categoryOld =  categoryMapper.selectByName(updateCategory.getName());
          if(categoryOld!=null&&categoryOld.getId().equals(updateCategory.getId())){
              throw new ImoocMallException(ImoocMailExceptionEnum.NAME_EXISTED);
          }
         int count =  categoryMapper.updateByPrimaryKeySelective(updateCategory);
          if(count==0){
              throw new ImoocMallException(ImoocMailExceptionEnum.UPDATE_FAILED);
          }
        }
    }
}
