package com.application.ev_platform.modules.vehicle;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository repo;

    public VehicleService(VehicleRepository repo) {
        this.repo = repo;
    }

    // GET all vehicles
    @Transactional(readOnly = true)
    public List<VehicleResponseDTO> getAllVehicles() {
        return repo.findAll().stream()
                .map(VehicleResponseDTO::new)
                .toList();
    }

    // GET vehicle by ID
    @Transactional(readOnly = true)
    public Optional<VehicleResponseDTO> getVehicleById(Long id) {
        return repo.findById(id).map(VehicleResponseDTO::new);
    }

    // GET vehicles by user
    @Transactional(readOnly = true)
    public List<VehicleResponseDTO> getVehiclesByUser(Long userId) {
        return repo.findByUserId(userId).stream()
                .map(VehicleResponseDTO::new)
                .toList();
    }

    // GET user's active vehicles
    @Transactional(readOnly = true)
    public List<VehicleResponseDTO> getActiveVehiclesByUser(Long userId) {
        return repo.findByUserIdAndActiveTrue(userId).stream()
                .map(VehicleResponseDTO::new)
                .toList();
    }

    // POST - Create vehicle
    @Transactional
    public VehicleResponseDTO createVehicle(VehicleRequestDTO request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setUserId(request.getUserId());
        vehicle.setMake(request.getMake());
        vehicle.setModel(request.getModel());
        vehicle.setRegistrationNumber(request.getRegistrationNumber());
        vehicle.setColor(request.getColor());
        vehicle.setBatteryCapacityKwh(request.getBatteryCapacityKwh());
        vehicle.setConnectorType(request.getConnectorType());
        vehicle.setActive(true);
        
        return new VehicleResponseDTO(repo.save(vehicle));
    }

    // PUT - Update vehicle
    @Transactional
    public VehicleResponseDTO updateVehicle(Long id, VehicleRequestDTO request) {
        Vehicle vehicle = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        
        if (request.getMake() != null) vehicle.setMake(request.getMake());
        if (request.getModel() != null) vehicle.setModel(request.getModel());
        if (request.getColor() != null) vehicle.setColor(request.getColor());
        if (request.getBatteryCapacityKwh() != null) vehicle.setBatteryCapacityKwh(request.getBatteryCapacityKwh());
        if (request.getConnectorType() != null) vehicle.setConnectorType(request.getConnectorType());
        
        return new VehicleResponseDTO(repo.save(vehicle));
    }

    // DELETE - Soft delete
    @Transactional
    public void deactivateVehicle(Long id) {
        Vehicle vehicle = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        vehicle.setActive(false);
        repo.save(vehicle);
    }

    // DELETE - Hard delete
    @Transactional
    public void deleteVehicle(Long id) {
        repo.deleteById(id);
    }
}
