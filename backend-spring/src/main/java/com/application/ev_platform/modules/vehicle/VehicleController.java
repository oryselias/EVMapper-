package com.application.ev_platform.modules.vehicle;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    // GET /api/vehicles - Get all vehicles
    @GetMapping
    public List<VehicleResponseDTO> getAllVehicles() {
        return service.getAllVehicles();
    }

    // GET /api/vehicles/{id} - Get vehicle by ID
    @GetMapping("/{id}")
    public VehicleResponseDTO getVehicleById(@PathVariable Long id) {
        return service.getVehicleById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    // GET /api/vehicles/user/{userId} - Get vehicles by user
    @GetMapping("/user/{userId}")
    public List<VehicleResponseDTO> getVehiclesByUser(@PathVariable Long userId) {
        return service.getVehiclesByUser(userId);
    }

    // GET /api/vehicles/user/{userId}/active - Get user's active vehicles
    @GetMapping("/user/{userId}/active")
    public List<VehicleResponseDTO> getActiveVehiclesByUser(@PathVariable Long userId) {
        return service.getActiveVehiclesByUser(userId);
    }

    // POST /api/vehicles - Create vehicle
    @PostMapping
    public VehicleResponseDTO createVehicle(@RequestBody VehicleRequestDTO request) {
        return service.createVehicle(request);
    }

    // PUT /api/vehicles/{id} - Update vehicle
    @PutMapping("/{id}")
    public VehicleResponseDTO updateVehicle(@PathVariable Long id, @RequestBody VehicleRequestDTO request) {
        return service.updateVehicle(id, request);
    }

    // DELETE /api/vehicles/{id} - Soft delete
    @DeleteMapping("/{id}")
    public void deactivateVehicle(@PathVariable Long id) {
        service.deactivateVehicle(id);
    }
}
