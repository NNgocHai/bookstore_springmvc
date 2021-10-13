package com.bookstore.service;


import com.bookstore.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> findAll();
    Integer deleteList(List<Integer> ids);
    CustomerEntity save(CustomerEntity T);
    CustomerEntity update(CustomerEntity T);
    CustomerEntity findById(int var1);
    List<CustomerEntity> findByUser(String user);

}
