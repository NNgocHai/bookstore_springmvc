package com.bookstore.service;

import com.bookstore.entity.ShipperEntity;

import java.util.List;

public interface ShipperService {

    List<ShipperEntity> findAll();
    Integer deleteList(List<Integer> ids);
    ShipperEntity save(ShipperEntity T);
    ShipperEntity update(ShipperEntity T);
    ShipperEntity findById(int var1);
    boolean checkShipperLogin(String username, String password);
    List<ShipperEntity> findByUser(String user);
    List<ShipperEntity> findID();
}
