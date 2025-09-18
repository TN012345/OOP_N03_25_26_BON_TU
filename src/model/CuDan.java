package main.model;

import java.io.Serializable;

public class CuDan implements Serializable {
    private String maCuDan;
    private String hoTen;
    private String ngaySinh;
    private String sdt;
    private String maCanHo;

    public CuDan(String maCuDan, String hoTen, String ngaySinh, String sdt, String maCanHo) {
        this.maCuDan = maCuDan;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.maCanHo = maCanHo;
    }

    public String getMaCuDan() { return maCuDan; }
    public String getHoTen() { return hoTen; }
    public String getNgaySinh() { return ngaySinh; }
    public String getSdt() { return sdt; }
    public String getMaCanHo() { return maCanHo; }

    @Override
    public String toString() {
        return maCuDan + " - " + hoTen + " - " + maCanHo;
    }
}
