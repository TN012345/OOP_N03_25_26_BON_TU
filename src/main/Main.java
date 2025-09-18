package main;

import main.controller.MenuController;

public class Main {
    public static void main(String[] args) {
        showDescription();
        new MenuController().start();
    }

    private static void showDescription() {
        System.out.println("==================================");
        System.out.println(" 🏢 Hệ thống Quản Lý Khu Chung Cư ");
        System.out.println(" Quản lý: Cư dân | Căn hộ | Tài khoản | BQL ");
        System.out.println("==================================\n");
    }
}
