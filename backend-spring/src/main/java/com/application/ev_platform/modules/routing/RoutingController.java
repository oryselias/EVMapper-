package com.application.ev_platform.modules.routing;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RoutingController {

    private final RoutingService service;

    public RoutingController(RoutingService service) {
        this.service = service;
    }

    // GET /api/routes - Get all routes
    @GetMapping
    public List<RouteResponseDTO> getAllRoutes() {
        return service.getAllRoutes();
    }

    // GET /api/routes/{id} - Get route by ID
    @GetMapping("/{id}")
    public RouteResponseDTO getRouteById(@PathVariable Long id) {
        return service.getRouteById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }

    // GET /api/routes/user/{userId} - Get routes by user
    @GetMapping("/user/{userId}")
    public List<RouteResponseDTO> getRoutesByUser(@PathVariable Long userId) {
        return service.getRoutesByUser(userId);
    }

    // POST /api/routes - Calculate route
    @PostMapping
    public RouteResponseDTO calculateRoute(@RequestBody RouteRequestDTO request) {
        return service.calculateRoute(request);
    }

    // DELETE /api/routes/{id} - Delete route
    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable Long id) {
        service.deleteRoute(id);
    }
}
