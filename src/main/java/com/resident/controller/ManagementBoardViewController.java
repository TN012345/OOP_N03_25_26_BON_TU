package com.resident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementBoardViewController {
    @GetMapping("/api/management-boards/page")
    public String managementBoardPage() {
        return "management-board";
    }
}
