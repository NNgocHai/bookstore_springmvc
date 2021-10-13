package com.bookstore.service_impl;

import com.bookstore.dao.ReviewDao;
import com.bookstore.dao_impl.ReviewDao_impl;
import com.bookstore.entity.ReviewEntity;
import com.bookstore.service.ReviewService;

import java.util.List;

public class ReviewService_impl implements ReviewService {
    public ReviewDao reviewDao=new ReviewDao_impl();
    public List<ReviewEntity> findAll(){
        return reviewDao.findAll();
    }
    public Integer deleteList(List<Integer> ids){
        return reviewDao.deleteList(ids);
    }
    public ReviewEntity save(ReviewEntity T)
    {
        return reviewDao.update(T);
    }
    public ReviewEntity update(ReviewEntity T)
    {
        return reviewDao.update(T);
    }
    public ReviewEntity findById(int var1)
    {
        return reviewDao.findById(var1);
    }
}
