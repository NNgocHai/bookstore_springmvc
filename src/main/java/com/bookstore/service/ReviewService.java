package com.bookstore.service;


import com.bookstore.entity.ReviewEntity;

import java.util.List;

public interface ReviewService {
    List<ReviewEntity> findAll();
    Integer deleteList(List<Integer> ids);
    ReviewEntity save(ReviewEntity T);
    ReviewEntity update(ReviewEntity T);
    ReviewEntity findById(int var1);
}
