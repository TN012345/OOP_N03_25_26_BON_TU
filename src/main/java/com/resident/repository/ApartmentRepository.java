package com.resident.repository;

import com.resident.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Optional<Apartment> findByApartmentNumber(String apartmentNumber);
    List<Apartment> findByBuilding(String building);
    List<Apartment> findByType(String type);
    List<Apartment> findByStatus(String status);
}
