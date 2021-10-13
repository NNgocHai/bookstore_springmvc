package com.bookstore.service_impl;

import com.bookstore.dao.CategoryDao;
import com.bookstore.dao_impl.CategoryDao_impl;
import com.bookstore.entity.CategoryEntity;
import com.bookstore.service.CategoryService;

import java.util.List;

public class CategoryService_impl implements CategoryService  {

    public CategoryDao categoryDao=new CategoryDao_impl();
    public List<CategoryEntity> findAll(){
        return categoryDao.findAll();
    }
    public Integer deleteList(List<Integer> ids){
        return categoryDao.deleteList(ids);
    }
    public CategoryEntity save(CategoryEntity T){
        return categoryDao.save(T);
    }
    public CategoryEntity update(CategoryEntity T){
        return categoryDao.update(T);
    }
    public  CategoryEntity findById(int var1)
    {
        return categoryDao.findById(var1);
    }
}
