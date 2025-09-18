package main.model;

import java.io.Serializable;

public class CanHo implements Serializable {
    private String maCanHo;
    private double dienTich;
    private int soPhongNgu;
    private String tinhTrang;

    public CanHo(String maCanHo, double dienTich, int soPhongNgu, String tinhTrang) {
        this.maCanHo = maCanHo;
        this.dienTich = dienTich;
        this.soPhongNgu = soPhongNgu;
        this.tinhTrang = tinhTrang;
    }

    public String getMaCanHo() { return maCanHo; }
    public double getDienTich() { return dienTich; }
    public int getSoPhongNgu() { return soPhongNgu; }
    public String getTinhTrang() { return tinhTrang; }

    public void setTinhTrang(String tinhTrang) { this.tinhTrang = tinhTrang; }

    @Override
    public String toString() {
        return maCanHo + " - " + dienTich + "m2 - " + soPhongNgu + "PN - " + tinhTrang;
    }
}
