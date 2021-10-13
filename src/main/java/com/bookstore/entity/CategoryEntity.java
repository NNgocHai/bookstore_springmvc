package com.bookstore.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="dausach")
public class CategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_DauSach")
    private Integer ma_DauSach;
    @Column(name = "ten_DauSach")
    private String ten_DauSach;


    @OneToMany(mappedBy = "categoryEntity" , fetch = FetchType.LAZY)
    private List<CuonSachEntity> cuonSachEntities;

    public List<CuonSachEntity> getCuonSachEntities() {
        return cuonSachEntities;
    }

    public void setCuonSachEntities(List<CuonSachEntity> cuonSachEntities) {
        this.cuonSachEntities = cuonSachEntities;
    }

    public Integer getMa_DauSach() {
        return ma_DauSach;
    }

    public void setMa_DauSach(Integer ma_DauSach) {
        this.ma_DauSach = ma_DauSach;
    }

    public String getTen_DauSach() {
        return ten_DauSach;
    }

    public void setTen_DauSach(String ten_DauSach) {
        this.ten_DauSach = ten_DauSach;
    }


}
