package com.bookstore.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "giohang")
public class GioHangEntity implements Serializable {
    @EmbeddedId
    private GioHangIDKey id;

    @Column(name="soluong")
    private Integer soluong;

    @ManyToOne
    @JoinColumn(name = "ma_Customer", insertable=false, updatable=false)
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "ma_CuonSach", insertable=false, updatable=false)
    private CuonSachEntity cuonSachEntity;

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public CuonSachEntity getCuonSachEntity() {
        return cuonSachEntity;
    }

    public void setCuonSachEntity(CuonSachEntity cuonSachEntity) {
        this.cuonSachEntity = cuonSachEntity;
    }

    public GioHangIDKey getId() {
        return id;
    }

    public void setId(GioHangIDKey id) {
        this.id = id;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

}
