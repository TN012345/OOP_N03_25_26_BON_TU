package com.phenikaa.quanly.controller;

import com.phenikaa.quanly.model.Apartment;
import com.phenikaa.quanly.repository.ApartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {
    private final ApartmentRepository repo;
    public ApartmentController(ApartmentRepository repo){ this.repo = repo; }

    @GetMapping
    public String list(Model m){
        m.addAttribute("apartments", repo.findAll());
        return "apartments/list";
    }
    @GetMapping("/new")
    public String createForm(Model m){
        m.addAttribute("apartment", new Apartment());
        return "apartments/form";
    }
    @PostMapping
    public String save(Apartment apartment){
        repo.save(apartment);
        return "redirect:/apartments";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model m){
        m.addAttribute("apartment", repo.findById(id).orElse(new Apartment()));
        return "apartments/form";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        repo.deleteById(id);
        return "redirect:/apartments";
    }
}
