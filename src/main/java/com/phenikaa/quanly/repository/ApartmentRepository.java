package com.phenikaa.quanly.repository;

import com.phenikaa.quanly.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
