package com.resident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountViewController {
    @GetMapping("/api/accounts/page")
    public String accountPage() {
        return "account";
    }
}
