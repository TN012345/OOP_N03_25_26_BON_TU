package com.phenikaa.quanly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {

    @GetMapping
    public String listApartments(Model model) {
        model.addAttribute("apartments", new ArrayList<>());
        return "apartments/apartments";
    }

    @GetMapping("/add")
    public String addApartmentForm(Model model) {
        model.addAttribute("apartment", new Object());
        return "apartments/add";
    }

    @GetMapping("/edit/{id}")
    public String editApartmentForm(Model model) {
        model.addAttribute("apartment", new Object());
        return "apartments/edit";
    }
}
