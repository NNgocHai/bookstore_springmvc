package com.bookstore.dao;

import com.bookstore.entity.ShipperEntity;

import java.util.List;

public interface ShipperDao extends GenericDao<Integer, ShipperEntity> {
    boolean checkShipperLogin(String username, String password);
    List<ShipperEntity> findByUser(String user);
    List<ShipperEntity> findID();
}
