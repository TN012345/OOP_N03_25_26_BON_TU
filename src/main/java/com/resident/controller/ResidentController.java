package com.resident.controller;

import com.resident.model.Resident;
import com.resident.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/residents")
@CrossOrigin(origins = "*")
public class ResidentController {
    @Autowired
    private ResidentService residentService;
    
    @GetMapping
    public ResponseEntity<?> getAllResidents() {
        try {
            List<Resident> residents = residentService.getAllResidents();
            return ResponseEntity.ok(residents);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi lấy danh sách cư dân: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getResidentById(@PathVariable Long id) {
        try {
            Optional<Resident> resident = residentService.getResidentById(id);
            if (resident.isPresent()) {
                return ResponseEntity.ok(resident.get());
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy cư dân với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi lấy thông tin cư dân: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createResident(@RequestBody Resident resident) {
        try {
            Resident savedResident = residentService.saveResident(resident);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Thêm cư dân thành công");
            response.put("data", savedResident);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi thêm cư dân: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateResident(@PathVariable Long id, @RequestBody Resident resident) {
        try {
            Optional<Resident> existingResident = residentService.getResidentById(id);
            if (existingResident.isPresent()) {
                resident.setId(id);
                Resident updatedResident = residentService.saveResident(resident);
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Cập nhật cư dân thành công");
                response.put("data", updatedResident);
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy cư dân với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi cập nhật cư dân: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResident(@PathVariable Long id) {
        try {
            Optional<Resident> resident = residentService.getResidentById(id);
            if (resident.isPresent()) {
                residentService.deleteResident(id);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Xóa cư dân thành công");
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy cư dân với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi xóa cư dân: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
}
