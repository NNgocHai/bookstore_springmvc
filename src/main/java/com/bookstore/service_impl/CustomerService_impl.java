package com.bookstore.service_impl;


import com.bookstore.dao.CustomerDao;
import com.bookstore.dao_impl.CustomerDao_impl;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class CustomerService_impl implements CustomerService {
    @Autowired
    public CustomerDao customerDao = new CustomerDao_impl();
    public List<CustomerEntity> findAll() {
        return customerDao.findAll();
    }

    public Integer deleteList(List<Integer> ids) {
        return customerDao.deleteList(ids);
    }




    public CustomerEntity save(CustomerEntity T) {
        return  customerDao.save(T);
    }

    public CustomerEntity update(CustomerEntity T) {
        return customerDao.update(T);

    }

    public CustomerEntity findById(int var1) {
        return customerDao.findById(var1);
    }

    @Override
    public List<CustomerEntity> findByUser(String user) {
        return customerDao.findByUser(user);    }
}

