package com.phenikaa.quanly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/bills")
public class BillController {

    @GetMapping
    public String listBills(Model model) {
        model.addAttribute("bills", new ArrayList<>());
        return "bills/bills";
    }

    @GetMapping("/add")
    public String addBillForm(Model model) {
        model.addAttribute("bill", new Object());
        return "bills/add";
    }

    @GetMapping("/edit/{id}")
    public String editBillForm(Model model) {
        model.addAttribute("bill", new Object());
        return "bills/edit";
    }
}
