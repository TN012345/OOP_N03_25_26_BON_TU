package com.resident.controller;

import com.resident.model.Apartment;
import com.resident.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/apartments")
@CrossOrigin(origins = "*")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;
    
    @GetMapping
    public ResponseEntity<?> getAllApartments() {
        try {
            List<Apartment> apartments = apartmentService.getAllApartments();
            return ResponseEntity.ok(apartments);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi lấy danh sách căn hộ: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getApartmentById(@PathVariable Long id) {
        try {
            Optional<Apartment> apartment = apartmentService.getApartmentById(id);
            if (apartment.isPresent()) {
                return ResponseEntity.ok(apartment.get());
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy căn hộ với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi lấy thông tin căn hộ: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createApartment(@RequestBody Apartment apartment) {
        try {
            Apartment savedApartment = apartmentService.saveApartment(apartment);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Thêm căn hộ thành công");
            response.put("data", savedApartment);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi thêm căn hộ: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateApartment(@PathVariable Long id, @RequestBody Apartment apartment) {
        try {
            Optional<Apartment> existingApartment = apartmentService.getApartmentById(id);
            if (existingApartment.isPresent()) {
                apartment.setId(id);
                Apartment updatedApartment = apartmentService.saveApartment(apartment);
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Cập nhật căn hộ thành công");
                response.put("data", updatedApartment);
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy căn hộ với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi cập nhật căn hộ: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApartment(@PathVariable Long id) {
        try {
            Optional<Apartment> apartment = apartmentService.getApartmentById(id);
            if (apartment.isPresent()) {
                apartmentService.deleteApartment(id);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Xóa căn hộ thành công");
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy căn hộ với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi xóa căn hộ: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
