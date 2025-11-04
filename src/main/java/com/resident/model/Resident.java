package com.resident.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "residents")
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String fullName;
    
    @Column(unique = true, length = 20)
    private String citizenId;
    
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    
    @Column(length = 10)
    private String gender;
    
    @Column(length = 100)
    private String email;
    
    @Column(length = 20)
    private String phoneNumber;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
    
    public Resident() {}
    
    public Resident(String fullName, String citizenId, LocalDate dateOfBirth, 
                   String gender, String email, String phoneNumber) {
        this.fullName = fullName;
        this.citizenId = citizenId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getCitizenId() {
        return citizenId;
    }
    
    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
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
    
    public Apartment getApartment() {
        return apartment;
    }
    
    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
