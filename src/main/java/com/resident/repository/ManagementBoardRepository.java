package com.resident.repository;

import com.resident.model.ManagementBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ManagementBoardRepository extends JpaRepository<ManagementBoard, Long> {
    List<ManagementBoard> findByNameContainingIgnoreCase(String name);
    List<ManagementBoard> findByStatus(String status);
}
