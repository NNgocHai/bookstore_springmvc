package com.bookstore.service_impl;

import com.bookstore.dao.ReviewDao;
import com.bookstore.dao_impl.ReviewDao_impl;
import com.bookstore.entity.ReviewEntity;
import com.bookstore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ReviewService_impl implements ReviewService {
    @Autowired
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
