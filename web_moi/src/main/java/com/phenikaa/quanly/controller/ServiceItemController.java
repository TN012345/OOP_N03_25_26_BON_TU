package com.phenikaa.quanly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/serviceitems")
public class ServiceItemController {

    @GetMapping
    public String listServiceItems(Model model) {
        model.addAttribute("serviceitems", new ArrayList<>());
        return "serviceitems/serviceitems";
    }

    @GetMapping("/add")
    public String addServiceItemForm(Model model) {
        model.addAttribute("serviceitem", new Object());
        return "serviceitems/add";
    }

    @GetMapping("/edit/{id}")
    public String editServiceItemForm(Model model) {
        model.addAttribute("serviceitem", new Object());
        return "serviceitems/edit";
    }
}
