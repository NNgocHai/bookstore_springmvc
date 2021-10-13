package com.bookstore.service_impl;

import com.bookstore.dao.AdminDao;
import com.bookstore.dao_impl.AdminDao_impl;
import com.bookstore.entity.AdminsEntity;
import com.bookstore.service.AdminService;

import java.util.List;

public class AdminService_impl implements AdminService {
    public AdminDao adminDao = new AdminDao_impl();
    public List<AdminsEntity> findAll() {
        //AdminDao adminDao = new AdminDao_impl();
        return adminDao.findAll();
    }

    public Integer deleteList(List<Integer> ids) {
        //AdminDao adminDao = new AdminDao_impl();
        return adminDao.deleteList(ids);
    }

    public boolean checkAdminLogin(String username, String password) {
        //AdminDao adminDao = new AdminDao_impl();
        return adminDao.checkAdminLogin(username, password);
    }

    public boolean checkDelete(String username, Integer id) {
        //AdminDao adminDao = new AdminDao_impl();
        return adminDao.checkDelete(username, id);
    }

    public AdminsEntity save(AdminsEntity T) {
        //AdminDao adminDao = new AdminDao_impl();
        return  adminDao.save(T);
    }

    public AdminsEntity update(AdminsEntity T) {
        //AdminDao adminDao = new AdminDao_impl();
        return adminDao.update(T);

    }

    public AdminsEntity findById(int var1) {
        return adminDao.findById(var1);
    }
}
