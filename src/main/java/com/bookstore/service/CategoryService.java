package com.bookstore.service;

import com.bookstore.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> findAll();
    Integer deleteList(List<Integer> ids);
    CategoryEntity save(CategoryEntity T);
    CategoryEntity update(CategoryEntity T);
    CategoryEntity findById(int var1);
}
