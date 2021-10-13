package com.bookstore.service;

import com.bookstore.entity.GiaoHangEntity;

import java.util.List;

public interface GiaoHangService {
    List<GiaoHangEntity> findID(Integer id);
    List<GiaoHangEntity> findID_DG(Integer id);
    List<GiaoHangEntity> findTT_GH();
    GiaoHangEntity save(GiaoHangEntity T);

}
