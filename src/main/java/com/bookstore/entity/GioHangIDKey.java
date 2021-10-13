package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GioHangIDKey implements Serializable {
    @Column
    private Integer ma_Customer;

    @Column
    private Integer ma_CuonSach;

    public Integer getMa_Customer() {
        return ma_Customer;
    }

    public void setMa_Customer(Integer ma_Customer) {
        this.ma_Customer = ma_Customer;
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
        GioHangIDKey that = (GioHangIDKey) o;
        return Objects.equals(ma_Customer, that.ma_Customer) &&
                Objects.equals(ma_CuonSach, that.ma_CuonSach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ma_Customer, ma_CuonSach);
    }
}
