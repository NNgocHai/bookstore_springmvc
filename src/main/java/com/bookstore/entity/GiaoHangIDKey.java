package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GiaoHangIDKey implements Serializable {
    @Column
    private  Integer ma_DH;

    @Column
    private Integer ma_Shipper;

    public Integer getMa_DH() {
        return ma_DH;
    }

    public void setMa_DH(Integer ma_DH) {
        this.ma_DH = ma_DH;
    }

    public Integer getMa_Shiper() {
        return ma_Shipper;
    }

    public void setMa_Shiper(Integer ma_Shiper) {
        this.ma_Shipper = ma_Shiper;
    }
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiaoHangIDKey that = (GiaoHangIDKey) o;
        return Objects.equals(ma_DH, that.ma_DH) &&
                Objects.equals(ma_Shipper, that.ma_Shipper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ma_DH, ma_Shipper);
    }
}
