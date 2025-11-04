package com.resident.service;

import com.resident.model.ManagementBoard;
import com.resident.repository.ManagementBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagementBoardService {
    @Autowired
    private ManagementBoardRepository managementBoardRepository;
    
    public List<ManagementBoard> getAllManagementBoards() {
        try {
            return managementBoardRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách ban quản lý: " + e.getMessage(), e);
        }
    }
    
    public Optional<ManagementBoard> getManagementBoardById(Long id) {
        try {
            return managementBoardRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy thông tin ban quản lý: " + e.getMessage(), e);
        }
    }
    
    public ManagementBoard saveManagementBoard(ManagementBoard managementBoard) {
        try {
            return managementBoardRepository.save(managementBoard);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu thông tin ban quản lý: " + e.getMessage(), e);
        }
    }
    
    public void deleteManagementBoard(Long id) {
        try {
            managementBoardRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa ban quản lý: " + e.getMessage(), e);
        }
    }
}
