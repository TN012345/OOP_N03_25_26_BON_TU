package com.phenikaa.quanly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // nếu có thêm trang khác
    }
}
