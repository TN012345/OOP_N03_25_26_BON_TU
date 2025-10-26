package com.phenikaa.quanly.controller;

import com.phenikaa.quanly.model.ServiceItem;
import com.phenikaa.quanly.repository.ServiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/serviceitems")
public class ServiceItemController {

    @Autowired
    private ServiceItemRepository serviceItemRepository;

    @GetMapping
    public String listServiceItems(Model model) {
        model.addAttribute("serviceitems", serviceItemRepository.findAll());
        return "serviceitems/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("serviceitem", new ServiceItem());
        return "serviceitems/form";
    }

    @PostMapping
    public String saveServiceItem(@ModelAttribute ServiceItem serviceItem) {
        serviceItemRepository.save(serviceItem);
        return "redirect:/serviceitems";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        ServiceItem serviceItem = serviceItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid service item Id:" + id));
        model.addAttribute("serviceitem", serviceItem);
        return "serviceitems/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteServiceItem(@PathVariable("id") Long id) {
        serviceItemRepository.deleteById(id);
        return "redirect:/serviceitems";
    }
}
