package com.example.admin.cuahangthietbionline.Class;

public class SanPham {
    private int id;
    private String tensp;
    private int GiaTien;
    private String hinhanh;

    public int getId() {
        return id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public SanPham(String tensp, int giaTien, String hinhanh) {
        this.tensp = tensp;
        GiaTien = giaTien;
        this.hinhanh = hinhanh;
    }

    public SanPham() {
        this.tensp = "";
        GiaTien = 0;
        this.hinhanh = "";
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "id=" + id +
                ", tensp='" + tensp + '\'' +
                ", GiaTien=" + GiaTien +
                ", hinhanh='" + hinhanh + '\'' +
                '}';
    }
}
