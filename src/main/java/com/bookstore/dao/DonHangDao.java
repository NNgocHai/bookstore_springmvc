package com.bookstore.dao;

import com.bookstore.entity.DonHangEntity;

import java.util.List;


public interface DonHangDao extends GenericDao<Integer, DonHangEntity> {
    List<DonHangEntity> Find_DHCG();
}

