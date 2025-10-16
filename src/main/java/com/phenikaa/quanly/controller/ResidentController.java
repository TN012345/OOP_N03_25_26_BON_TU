package com.phenikaa.quanly.controller;

import com.phenikaa.quanly.model.Resident;
import com.phenikaa.quanly.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/residents")
public class ResidentController {

    @Autowired
    private ResidentRepository residentRepository;

    // Danh sách cư dân
    @GetMapping
    public String listResidents(Model model) {
        model.addAttribute("residents", residentRepository.findAll());
        return "residents/list";
    }

    // Form thêm cư dân
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("resident", new Resident());
        return "residents/form";
    }

    // Lưu cư dân
    @PostMapping
    public String saveResident(@ModelAttribute Resident resident) {
        residentRepository.save(resident);
        return "redirect:/residents";
    }

    // Form sửa cư dân
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resident Id:" + id));
        model.addAttribute("resident", resident);
        return "residents/form";
    }

    // Xóa cư dân
    @GetMapping("/delete/{id}")
    public String deleteResident(@PathVariable("id") Long id) {
        residentRepository.deleteById(id);
        return "redirect:/residents";
    }
}
