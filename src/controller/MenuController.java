package main.controller;

import java.util.Scanner;
import main.service.*;

public class MenuController {
    private CuDanService cuDanService = new CuDanService();
    private CanHoService canHoService = new CanHoService();
    private TaiKhoanService taiKhoanService = new TaiKhoanService();
    private BQLService bqlService = new BQLService();

    public void start() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("===== MENU CHÍNH =====");
            System.out.println("1. Quản lý cư dân");
            System.out.println("2. Quản lý căn hộ");
            System.out.println("3. Quản lý tài khoản");
            System.out.println("4. Quản lý BQL");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> cuDanService.menu();
                case 2 -> canHoService.menu();
                case 3 -> taiKhoanService.menu();
                case 4 -> bqlService.menu();
                case 0 -> System.out.println("Thoát chương trình!");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}
