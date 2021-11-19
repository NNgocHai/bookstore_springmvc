package com.bookstore.service_impl;

import com.bookstore.dao.ShipperDao;
import com.bookstore.dao_impl.ShipperDao_impl;
import com.bookstore.entity.ShipperEntity;
import com.bookstore.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ShipperService_impl implements ShipperService {
    @Autowired
    public ShipperDao shipperDao=new ShipperDao_impl();
    public List<ShipperEntity> findAll(){
        return shipperDao.findAll();
    }
    public Integer deleteList(List<Integer> ids){
        return shipperDao.deleteList(ids);
    }
    public ShipperEntity save(ShipperEntity T)
    {
        return shipperDao.update(T);
    }
    public ShipperEntity update(ShipperEntity T)
    {
        return shipperDao.update(T);
    }
    public ShipperEntity findById(int var1)
    {
        return shipperDao.findById(var1);
    }
    public boolean checkShipperLogin(String username, String password) { return shipperDao.checkShipperLogin(username,password); }
    public List<ShipperEntity> findByUser(String user) { return shipperDao.findByUser(user); }

    @Override
    public List<ShipperEntity> findID() {
        return shipperDao.findID();
    }
}
