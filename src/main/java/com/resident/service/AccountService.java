package com.resident.service;

import com.resident.model.Account;
import com.resident.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    
    public List<Account> getAllAccounts() {
        try {
            return accountRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách tài khoản: " + e.getMessage(), e);
        }
    }
    
    public Optional<Account> getAccountById(Long id) {
        try {
            return accountRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy thông tin tài khoản: " + e.getMessage(), e);
        }
    }
    
    public Optional<Account> getAccountByUsername(String username) {
        try {
            return accountRepository.findByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm tài khoản: " + e.getMessage(), e);
        }
    }
    
    public Account saveAccount(Account account) {
        try {
            // Set createdAt if new account
            if (account.getId() == null && account.getCreatedAt() == null) {
                account.setCreatedAt(LocalDateTime.now());
            }
            return accountRepository.save(account);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu thông tin tài khoản: " + e.getMessage(), e);
        }
    }
    
    public void deleteAccount(Long id) {
        try {
            accountRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa tài khoản: " + e.getMessage(), e);
        }
    }
}
