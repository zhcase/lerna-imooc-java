package com.imooc.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mall.exception.ImoocMailExceptionEnum;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.dao.CategoryMapper;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.model.vo.CategoryVo;
import com.imooc.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import springfox.documentation.annotations.Cacheable;

import java.util.ArrayList;
import java.util.List;

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
        if (updateCategory.getName() != null) {
            Category categoryOld = categoryMapper.selectByName(updateCategory.getName());
            if (categoryOld != null && categoryOld.getId().equals(updateCategory.getId())) {
                throw new ImoocMallException(ImoocMailExceptionEnum.NAME_EXISTED);
            }
            int count = categoryMapper.updateByPrimaryKeySelective(updateCategory);
            if (count == 0) {
                throw new ImoocMallException(ImoocMailExceptionEnum.UPDATE_FAILED);
            }
        }
    }

    @Override
    public void delete(Integer id) throws ImoocMallException {
        Category categoryOld = categoryMapper.selectByPrimaryKey(id);
//       查不到分类
        if (categoryOld == null) {
            throw new ImoocMallException(ImoocMailExceptionEnum.DELETE_FAILD);
        }
        int count = categoryMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new ImoocMallException(ImoocMailExceptionEnum.DELETE_FAILD);
        }
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "type,order_num");
        List<Category> categoryList = categoryMapper.selectList();
        PageInfo pageInfo = new PageInfo(categoryList);
        return pageInfo;
    }

    @Override
    @Cacheable(value="listCategoryForCustomer")
    public List<CategoryVo> listCategoryForCustomer() {
        ArrayList<CategoryVo> categoryVoList = new ArrayList<>();
        recursivelyFindCategories(categoryVoList, 0);
        return categoryVoList;
    }

    private void recursivelyFindCategories(List<CategoryVo> categoryVoList, Integer parentId) {
//        递归获取所有子类别 并组成一个“目录树”
        List<Category> categoryList = categoryMapper.selectCategoriesByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)) {
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVo);
                categoryVoList.add(categoryVo);
                recursivelyFindCategories(categoryVo.getChildCategory(), categoryVo.getId());
            }
        }
    }


}
