package com.bookstore.service_impl;


import com.bookstore.dao_impl.ProductDao_impl;
import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.service.ProductService;

import java.util.List;

public class ProductService_impl implements ProductService {
    public ProductDao_impl productDao = new ProductDao_impl();
    public List<CuonSachEntity> findAll() {
        return productDao.findAll();
    }

    public Integer deleteList(List<Integer> ids) {
        return productDao.deleteList(ids);
    }
    public CuonSachEntity save(CuonSachEntity T) {
        return  productDao.save(T);
    }

    public CuonSachEntity update(CuonSachEntity T) {
        return productDao.update(T);
    }

    public CuonSachEntity findById(int var1) {
        return productDao.findById(var1);
    }

    @Override
    public List<CuonSachEntity> FindByCate(int Cate) {
        return productDao.FindByCate(Cate);
    }

    @Override
    public List<ChiTietDonHangEntity> FindHot() {
        return productDao.FindHot();
    }

    @Override
    public List<CuonSachEntity> FindHotDiscount() {
        return productDao.FindHotDiscount();
    }

    @Override
    public List<CuonSachEntity> Search(String TuKhoa) {
        return productDao.Search(TuKhoa);
    }
}
