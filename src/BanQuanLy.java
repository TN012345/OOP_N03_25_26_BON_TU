package main.model;

import java.io.Serializable;

public class BanQuanLy implements Serializable {
    private String maNV;
    private String hoTen;
    private String chucVu;
    private String sdt;

    public BanQuanLy(String maNV, String hoTen, String chucVu, String sdt) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.sdt = sdt;
    }

    public String getMaNV() { return maNV; }
    public String getHoTen() { return hoTen; }
    public String getChucVu() { return chucVu; }
    public String getSdt() { return sdt; }

    @Override
    public String toString() {
        return maNV + " - " + hoTen + " - " + chucVu + " - " + sdt;
    }
}
