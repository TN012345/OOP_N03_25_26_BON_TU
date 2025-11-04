package com.resident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApartmentViewController {
    @GetMapping("/api/apartments/page")
    public String apartmentPage() {
        return "apartment";
    }
}
