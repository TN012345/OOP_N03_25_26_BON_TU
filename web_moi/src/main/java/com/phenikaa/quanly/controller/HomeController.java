package com.phenikaa.quanly.controller;

import com.phenikaa.quanly.MyGlobal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index"})
    public String home(Model model) {
        // Gửi biến toàn cục sang giao diện để hiển thị thông báo nếu cần
        model.addAttribute("indexError", MyGlobal.indexError);
        return "index"; // hiển thị trang chủ
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        // Truyền biến toàn cục sang trang liên hệ (nếu có)
        model.addAttribute("indexError", MyGlobal.indexError);
        return "contacts"; // hiển thị trang liên hệ
    }
}
