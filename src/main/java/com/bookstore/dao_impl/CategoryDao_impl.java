package com.bookstore.dao_impl;

import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao_impl extends GenericDao_impl<Integer, CategoryEntity> implements CategoryDao {

}
