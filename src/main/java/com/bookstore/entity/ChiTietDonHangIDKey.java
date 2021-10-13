package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietDonHangIDKey implements Serializable {
    @Column
    private Integer ma_DH;
    @Column
    private Integer ma_CuonSach;

    public Integer getMa_DH() {
        return ma_DH;
    }

    public void setMa_DH(Integer ma_DH) {
        this.ma_DH = ma_DH;
    }

    public Integer getMa_CuonSach() {
        return ma_CuonSach;
    }

    public void setMa_CuonSach(Integer ma_CuonSach) {
        this.ma_CuonSach = ma_CuonSach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietDonHangIDKey that = (ChiTietDonHangIDKey) o;
        return Objects.equals(ma_DH, that.ma_DH) &&
                Objects.equals(ma_CuonSach, that.ma_CuonSach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ma_DH, ma_CuonSach);
    }

}
