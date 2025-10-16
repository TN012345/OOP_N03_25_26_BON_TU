package com.phenikaa.quanly.repository;

import com.phenikaa.quanly.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
