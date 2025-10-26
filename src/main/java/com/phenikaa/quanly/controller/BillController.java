package com.phenikaa.quanly.controller;

import com.phenikaa.quanly.model.Bill;
import com.phenikaa.quanly.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @GetMapping
    public String listBills(Model model) {
        model.addAttribute("bills", billRepository.findAll());
        return "bills/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bill", new Bill());
        return "bills/form";
    }

    @PostMapping
    public String saveBill(@ModelAttribute Bill bill) {
        billRepository.save(bill);
        return "redirect:/bills";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bill Id:" + id));
        model.addAttribute("bill", bill);
        return "bills/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBill(@PathVariable("id") Long id) {
        billRepository.deleteById(id);
        return "redirect:/bills";
    }
}
