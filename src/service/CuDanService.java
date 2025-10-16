package main.service;

import main.model.CuDan;
import java.util.ArrayList;
import java.util.List;

public class CuDanService {
    private List<CuDan> danhSach = new ArrayList<>();

    // CREATE
    public void themCuDan(CuDan cuDan) {
        danhSach.add(cuDan);
    }

    // READ
    public List<CuDan> getDanhSach() {
        return danhSach;
    }

    // UPDATE
    public boolean suaCuDan(String maCuDan, CuDan cuDanMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaCuDan().equals(maCuDan)) {
                danhSach.set(i, cuDanMoi);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean xoaCuDan(String maCuDan) {
        return danhSach.removeIf(c -> c.getMaCuDan().equals(maCuDan));
    }
}
