package com.resident.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 50, unique = true)
    private String username; // Tên đăng nhập
    
    @Column(nullable = false, length = 255)
    private String password; // Mật khẩu (đã hash)
    
    @Column(length = 100)
    private String email;
    
    @Column(length = 20)
    private String phoneNumber;
    
    @Column(length = 50)
    private String role; // Vai trò: Admin, Manager, Resident, Staff
    
    @Column(length = 20)
    private String status; // Trạng thái: Active, Inactive, Locked
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resident_id")
    private Resident resident; // Liên kết với cư dân (nếu là tài khoản của cư dân)
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "management_board_id")
    private ManagementBoard managementBoard; // Liên kết với ban quản lý
    
    @Column(nullable = false)
    private LocalDateTime createdAt; // Ngày tạo
    
    @Column
    private LocalDateTime lastLoginAt; // Lần đăng nhập cuối
    
    public Account() {
        this.createdAt = LocalDateTime.now();
    }
    
    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = "Active";
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Resident getResident() {
        return resident;
    }
    
    public void setResident(Resident resident) {
        this.resident = resident;
    }
    
    public ManagementBoard getManagementBoard() {
        return managementBoard;
    }
    
    public void setManagementBoard(ManagementBoard managementBoard) {
        this.managementBoard = managementBoard;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }
    
    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
}
