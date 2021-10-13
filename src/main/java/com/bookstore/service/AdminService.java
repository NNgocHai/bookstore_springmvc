package com.bookstore.service;

import com.bookstore.entity.AdminsEntity;

import java.util.List;

public interface AdminService {
    List<AdminsEntity> findAll();
    Integer deleteList(List<Integer> ids);
    boolean checkAdminLogin(String username, String password);
    boolean checkDelete(String username, Integer id);
    AdminsEntity save(AdminsEntity T);
    AdminsEntity update(AdminsEntity T);
    AdminsEntity findById(int var1);

}
