package com.resident.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 20, unique = true)
    private String apartmentNumber; // Số căn hộ: A101, B202, etc.
    
    @Column(nullable = false, length = 50)
    private String building; // Tòa nhà: Tòa A, Tòa B, etc.
    
    @Column(nullable = false)
    private Integer floor; // Tầng
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal area; // Diện tích (m²)
    
    @Column(length = 20)
    private String type; // Loại căn hộ: Studio, 1PN, 2PN, 3PN, etc.
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price; // Giá căn hộ
    
    @Column(length = 50)
    private String ownerName; // Tên chủ sở hữu
    
    @Column(length = 20)
    private String ownerPhone; // SĐT chủ sở hữu
    
    @Column(length = 20)
    private String status; // Trạng thái: Đã bán, Đang cho thuê, Trống
    
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private java.util.List<Resident> residents = new java.util.ArrayList<>();
    
    public Apartment() {}
    
    public Apartment(String apartmentNumber, String building, Integer floor, 
                    BigDecimal area, String type, BigDecimal price) {
        this.apartmentNumber = apartmentNumber;
        this.building = building;
        this.floor = floor;
        this.area = area;
        this.type = type;
        this.price = price;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getApartmentNumber() {
        return apartmentNumber;
    }
    
    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
    
    public String getBuilding() {
        return building;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }
    
    public Integer getFloor() {
        return floor;
    }
    
    public void setFloor(Integer floor) {
        this.floor = floor;
    }
    
    public BigDecimal getArea() {
        return area;
    }
    
    public void setArea(BigDecimal area) {
        this.area = area;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public String getOwnerPhone() {
        return ownerPhone;
    }
    
    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public java.util.List<Resident> getResidents() {
        return residents;
    }
    
    public void setResidents(java.util.List<Resident> residents) {
        this.residents = residents;
    }
}
