package com.resident.repository;

import com.resident.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
    Optional<Resident> findByCitizenId(String citizenId);
    List<Resident> findByFullNameContainingIgnoreCase(String name);
}
