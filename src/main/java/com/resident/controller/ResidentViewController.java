package com.resident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResidentViewController {
    @GetMapping("/api/residents/page")
    public String residentPage() {
        return "resident";
    }
}
