package com.resident.service;

import com.resident.model.Resident;
import com.resident.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResidentService {
    @Autowired
    private ResidentRepository residentRepository;
    
    public List<Resident> getAllResidents() {
        try {
            return residentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách cư dân: " + e.getMessage(), e);
        }
    }
    
    public Optional<Resident> getResidentById(Long id) {
        try {
            return residentRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy thông tin cư dân: " + e.getMessage(), e);
        }
    }
    
    public Resident saveResident(Resident resident) {
        try {
            return residentRepository.save(resident);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu thông tin cư dân: " + e.getMessage(), e);
        }
    }
    
    public void deleteResident(Long id) {
        try {
            residentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa cư dân: " + e.getMessage(), e);
        }
    }
}
