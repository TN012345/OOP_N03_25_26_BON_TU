package com.resident.controller;

import com.resident.model.ManagementBoard;
import com.resident.service.ManagementBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/management-boards")
@CrossOrigin(origins = "*")
public class ManagementBoardController {
    @Autowired
    private ManagementBoardService managementBoardService;
    
    @GetMapping
    public ResponseEntity<?> getAllManagementBoards() {
        try {
            List<ManagementBoard> boards = managementBoardService.getAllManagementBoards();
            return ResponseEntity.ok(boards);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi lấy danh sách ban quản lý: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getManagementBoardById(@PathVariable Long id) {
        try {
            Optional<ManagementBoard> board = managementBoardService.getManagementBoardById(id);
            if (board.isPresent()) {
                return ResponseEntity.ok(board.get());
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy ban quản lý với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi lấy thông tin ban quản lý: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createManagementBoard(@RequestBody ManagementBoard managementBoard) {
        try {
            ManagementBoard savedBoard = managementBoardService.saveManagementBoard(managementBoard);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Thêm ban quản lý thành công");
            response.put("data", savedBoard);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi thêm ban quản lý: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateManagementBoard(@PathVariable Long id, @RequestBody ManagementBoard managementBoard) {
        try {
            Optional<ManagementBoard> existingBoard = managementBoardService.getManagementBoardById(id);
            if (existingBoard.isPresent()) {
                managementBoard.setId(id);
                ManagementBoard updatedBoard = managementBoardService.saveManagementBoard(managementBoard);
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Cập nhật ban quản lý thành công");
                response.put("data", updatedBoard);
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy ban quản lý với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi cập nhật ban quản lý: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteManagementBoard(@PathVariable Long id) {
        try {
            Optional<ManagementBoard> board = managementBoardService.getManagementBoardById(id);
            if (board.isPresent()) {
                managementBoardService.deleteManagementBoard(id);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Xóa ban quản lý thành công");
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy ban quản lý với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lỗi khi xóa ban quản lý: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
