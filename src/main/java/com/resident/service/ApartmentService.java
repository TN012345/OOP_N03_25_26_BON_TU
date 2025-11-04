package com.resident.service;

import com.resident.model.Apartment;
import com.resident.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;
    
    public List<Apartment> getAllApartments() {
        try {
            return apartmentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách căn hộ: " + e.getMessage(), e);
        }
    }
    
    public Optional<Apartment> getApartmentById(Long id) {
        try {
            return apartmentRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy thông tin căn hộ: " + e.getMessage(), e);
        }
    }
    
    public Apartment saveApartment(Apartment apartment) {
        try {
            return apartmentRepository.save(apartment);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu thông tin căn hộ: " + e.getMessage(), e);
        }
    }
    
    public void deleteApartment(Long id) {
        try {
            apartmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa căn hộ: " + e.getMessage(), e);
        }
    }
}
