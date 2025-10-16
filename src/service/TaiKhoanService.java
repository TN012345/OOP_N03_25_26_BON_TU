package main.service;

import main.model.TaiKhoan;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanService {
    private List<TaiKhoan> danhSach = new ArrayList<>();

    // CREATE
    public void taoTaiKhoan(TaiKhoan tk) {
        danhSach.add(tk);
    }

    // READ
    public List<TaiKhoan> getDanhSach() {
        return danhSach;
    }

    // UPDATE
    public boolean doiMatKhau(String username, String newPasswordHash) {
        for (TaiKhoan tk : danhSach) {
            if (tk.getUsername().equals(username)) {
                tk.setPasswordHash(newPasswordHash);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean xoaTaiKhoan(String username) {
        return danhSach.removeIf(tk -> tk.getUsername().equals(username));
    }
}
