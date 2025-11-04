package com.resident.controller;

import com.resident.model.Account;
import com.resident.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {
    @Autowired
    private AccountService accountService;
    
    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        try {
            List<Account> accounts = accountService.getAllAccounts();
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi lấy danh sách tài khoản: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {
        try {
            Optional<Account> account = accountService.getAccountById(id);
            if (account.isPresent()) {
                return ResponseEntity.ok(account.get());
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy tài khoản với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi lấy thông tin tài khoản: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getAccountByUsername(@PathVariable String username) {
        try {
            Optional<Account> account = accountService.getAccountByUsername(username);
            if (account.isPresent()) {
                return ResponseEntity.ok(account.get());
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy tài khoản với username: " + username);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi tìm tài khoản: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        try {
            Account savedAccount = accountService.saveAccount(account);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Thêm tài khoản thành công");
            response.put("data", savedAccount);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi thêm tài khoản: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        try {
            Optional<Account> existingAccount = accountService.getAccountById(id);
            if (existingAccount.isPresent()) {
                account.setId(id);
                Account updatedAccount = accountService.saveAccount(account);
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Cập nhật tài khoản thành công");
                response.put("data", updatedAccount);
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy tài khoản với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi cập nhật tài khoản: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        try {
            Optional<Account> account = accountService.getAccountById(id);
            if (account.isPresent()) {
                accountService.deleteAccount(id);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Xóa tài khoản thành công");
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy tài khoản với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi xóa tài khoản: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
