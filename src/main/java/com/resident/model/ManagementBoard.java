package com.resident.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "management_boards")
public class ManagementBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name; // Tên ban quản lý
    
    @Column(length = 200)
    private String address; // Địa chỉ văn phòng
    
    @Column(length = 20)
    private String phoneNumber; // Số điện thoại
    
    @Column(length = 100)
    private String email; // Email
    
    @Column(length = 50)
    private String directorName; // Tên giám đốc
    
    @Column(length = 20)
    private String directorPhone; // SĐT giám đốc
    
    @Column(nullable = false)
    private LocalDate establishedDate; // Ngày thành lập
    
    @Column(length = 500)
    private String description; // Mô tả
    
    @Column(length = 20)
    private String status; // Trạng thái: Hoạt động, Ngừng hoạt động
    
    public ManagementBoard() {}
    
    public ManagementBoard(String name, String address, String phoneNumber, 
                          LocalDate establishedDate) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.establishedDate = establishedDate;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDirectorName() {
        return directorName;
    }
    
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    
    public String getDirectorPhone() {
        return directorPhone;
    }
    
    public void setDirectorPhone(String directorPhone) {
        this.directorPhone = directorPhone;
    }
    
    public LocalDate getEstablishedDate() {
        return establishedDate;
    }
    
    public void setEstablishedDate(LocalDate establishedDate) {
        this.establishedDate = establishedDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
