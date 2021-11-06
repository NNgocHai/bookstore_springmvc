package com.bookstore.dao_impl;

import com.bookstore.dao.ReviewDao;
import com.bookstore.entity.ReviewEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDao_impl extends GenericDao_impl<Integer, ReviewEntity> implements ReviewDao {
}
