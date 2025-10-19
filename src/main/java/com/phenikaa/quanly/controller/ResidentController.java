package com.phenikaa.quanly.controller;

import com.phenikaa.quanly.model.Resident;
import com.phenikaa.quanly.repository.ResidentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentRepository residentRepository;

    public ResidentController(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    // Danh sách cư dân
    @GetMapping
    public String listResidents(Model model) {
        model.addAttribute("residents", residentRepository.findAll());
        return "residents/list";
    }

    // Thêm cư dân
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("resident", new Resident());
        return "residents/add";
    }

    @PostMapping("/add")
    public String addResident(@ModelAttribute Resident resident) {
        residentRepository.save(resident);
        return "redirect:/residents";
    }

    // Xóa cư dân
    @GetMapping("/delete/{id}")
    public String deleteResident(@PathVariable Long id) {
        residentRepository.deleteById(id);
        return "redirect:/residents";
    }
}
