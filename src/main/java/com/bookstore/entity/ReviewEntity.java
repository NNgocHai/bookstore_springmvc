package com.bookstore.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="review")
public class ReviewEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_Review;

    @Column(name="ma_Customer")
    private Integer ma_Customer;

    @Column(name ="ngay_Review")
    private Timestamp ngay_Review;

    @Column(name="binhluan")
    private String binhluan;

    @ManyToOne
    @JoinColumn(name = "ma_Customer", insertable=false, updatable=false)
    private CustomerEntity customerEntity;

    public Integer getMa_Review() {
        return ma_Review;
    }

    public void setMa_Review(Integer ma_Review) {
        this.ma_Review = ma_Review;
    }

    public Integer getMa_Customer() {
        return ma_Customer;
    }

    public void setMa_Customer(Integer ma_Customer) {
        this.ma_Customer = ma_Customer;
    }

    public Timestamp getNgay_Review() {
        return ngay_Review;
    }

    public void setNgay_Review(Timestamp ngay_Review) {
        this.ngay_Review = ngay_Review;
    }

    public String getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(String binhluan) {
        this.binhluan = binhluan;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

}
