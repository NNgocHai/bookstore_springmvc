package com.bookstore.service_impl;

import com.bookstore.dao.DonHangDao;
import com.bookstore.dao_impl.DonHangDao_impl;
import com.bookstore.entity.DonHangEntity;
import com.bookstore.service.DonHangService;

import java.util.List;

public class DonHangService_impl implements DonHangService {
    DonHangDao donHangDao = new DonHangDao_impl();
    @Override
    public DonHangEntity findById(int var1) {
        return donHangDao.findById(var1);
    }

    @Override
    public DonHangEntity update(DonHangEntity donHangEntity) {
        return donHangDao.update(donHangEntity);
    }

    @Override
    public List<DonHangEntity> Find_DHCG() {
        return donHangDao.Find_DHCG();
    }

    @Override
    public DonHangEntity save(DonHangEntity T) {
        return donHangDao.save(T);
    }

    public List<DonHangEntity> findAll(){
        return donHangDao.findAll();
    }
    public Integer deleteList(List<Integer> ids){
        return donHangDao.deleteList(ids);
    }
}
