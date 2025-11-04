package com.phenikaa.quanly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/residents")
public class ResidentController {

    @GetMapping
    public String listResidents(Model model) {
        model.addAttribute("residents", new ArrayList<>()); // Danh sách tạm trống
        return "residents/residents";
    }

    @GetMapping("/add")
    public String addResidentForm(Model model) {
        model.addAttribute("resident", new Object()); // Chưa có entity
        return "residents/add";
    }

    @GetMapping("/edit/{id}")
    public String editResidentForm(Model model) {
        model.addAttribute("resident", new Object()); // Mẫu edit
        return "residents/edit";
    }
}
