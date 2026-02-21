package com.application.ev_platform.modules.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByUserId(Long userId);
    List<Vehicle> findByUserIdAndActiveTrue(Long userId);
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
}
