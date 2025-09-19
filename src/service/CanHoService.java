package main.service;

import main.model.CanHo;
import java.util.ArrayList;
import java.util.List;

public class CanHoService {
    private List<CanHo> danhSach = new ArrayList<>();

    // CREATE
    public void themCanHo(CanHo canHo) {
        danhSach.add(canHo);
    }

    // READ
    public List<CanHo> getDanhSach() {
        return danhSach;
    }

    // UPDATE
    public boolean suaCanHo(String maCanHo, CanHo canHoMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaCanHo().equals(maCanHo)) {
                danhSach.set(i, canHoMoi);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean xoaCanHo(String maCanHo) {
        return danhSach.removeIf(c -> c.getMaCanHo().equals(maCanHo));
    }
}
