package com.bookstore.dao;

import com.bookstore.entity.CustomerEntity;

import java.util.List;

public interface CustomerDao extends GenericDao<Integer, CustomerEntity> {
    boolean checkCustomerLogin(String username, String password);

    List<CustomerEntity> findByUser(String user);

    boolean checkAddCustomer(String username, String gmail, String sdt);
    boolean checkUserName(String username);
    boolean checkGmail(String gmail);
    boolean checkSdt(String sdt);
    boolean checkActiveAccount(String key1, String key2);


}
