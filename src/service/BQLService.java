package main.service;

import main.model.BanQuanLy;
import java.util.ArrayList;
import java.util.List;

public class BQLService {
    private List<BanQuanLy> danhSach = new ArrayList<>();

    // CREATE
    public void themNhanVien(BanQuanLy bql) {
        danhSach.add(bql);
    }

    // READ
    public List<BanQuanLy> getDanhSach() {
        return danhSach;
    }

    // UPDATE
    public boolean suaNhanVien(String maNV, BanQuanLy bqlMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaNV().equals(maNV)) {
                danhSach.set(i, bqlMoi);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean xoaNhanVien(String maNV) {
        return danhSach.removeIf(bql -> bql.getMaNV().equals(maNV));
    }
}
