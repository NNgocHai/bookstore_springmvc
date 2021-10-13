package com.bookstore.dao;

import com.bookstore.entity.AdminsEntity;

public interface AdminDao extends GenericDao<Integer, AdminsEntity>{
    boolean checkAdminLogin(String username, String password);
    boolean checkDelete(String username, Integer id);
}
