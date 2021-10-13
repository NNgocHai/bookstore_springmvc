package com.bookstore.service_impl;

import com.bookstore.dao.GiaoHangDao;
import com.bookstore.dao_impl.GiaoHangDao_impl;
import com.bookstore.entity.GiaoHangEntity;
import com.bookstore.service.GiaoHangService;

import java.util.List;

public class GiaoHangService_impl implements GiaoHangService {
    GiaoHangDao giaoHangDao = new GiaoHangDao_impl();
    @Override
    public List<GiaoHangEntity> findID(Integer id) {
        return giaoHangDao.findID(id);
    }

    @Override
    public List<GiaoHangEntity> findID_DG(Integer id) {
        return giaoHangDao.findID_DG(id);
    }

    @Override
    public List<GiaoHangEntity> findTT_GH() {
        return giaoHangDao.findTT_GH();
    }

    @Override
    public GiaoHangEntity save(GiaoHangEntity T) {
        return giaoHangDao.save(T);
    }

}
