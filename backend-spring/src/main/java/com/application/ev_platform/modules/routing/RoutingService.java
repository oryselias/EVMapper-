package com.application.ev_platform.modules.routing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RoutingService {

    private final RouteRepository repo;
    private final RestTemplate restTemplate;
    
    // OpenRouteService API (free tier) - user can replace with their own key
    @Value("${routing.api.key:}")
    private String routingApiKey;
    
    // Default to OSRM (free, no key required)
    private static final String OSRM_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";

    public RoutingService(RouteRepository repo) {
        this.repo = repo;
        this.restTemplate = new RestTemplate();
    }

    // GET all routes
    @Transactional(readOnly = true)
    public List<RouteResponseDTO> getAllRoutes() {
        return repo.findAll().stream()
                .map(RouteResponseDTO::new)
                .toList();
    }

    // GET route by ID
    @Transactional(readOnly = true)
    public Optional<RouteResponseDTO> getRouteById(Long id) {
        return repo.findById(id).map(RouteResponseDTO::new);
    }

    // GET routes by user
    @Transactional(readOnly = true)
    public List<RouteResponseDTO> getRoutesByUser(Long userId) {
        return repo.findByUserId(userId).stream()
                .map(RouteResponseDTO::new)
                .toList();
    }

    // POST - Calculate route between two points
    @Transactional
    public RouteResponseDTO calculateRoute(RouteRequestDTO request) {
        // Use OSRM for free routing
        String osrmUrl = String.format("%s%s,%s;%s,%s?overview=full&geometries=geojson",
            OSRM_BASE_URL,
            request.getStartLng(), request.getStartLat(),
            request.getEndLng(), request.getEndLat());
        
        try {
            // Make the API call
            OsrmResponse response = restTemplate.getForObject(osrmUrl, OsrmResponse.class);
            
            if (response != null && response.getRoutes() != null && !response.getRoutes().isEmpty()) {
                OsrmRoute osrmRoute = response.getRoutes().get(0);
                
                Route route = new Route();
                route.setUserId(request.getUserId());
                route.setStartStationId(request.getStartStationId());
                route.setEndStationId(request.getEndStationId());
                route.setStartLat(request.getStartLat());
                route.setStartLng(request.getStartLng());
                route.setEndLat(request.getEndLat());
                route.setEndLng(request.getEndLng());
                route.setGeometry(osrmRoute.getGeometry()); // GeoJSON
                route.setDistanceMeters(osrmRoute.getDistance());
                route.setDurationSeconds(osrmRoute.getDuration());
                
                return new RouteResponseDTO(repo.save(route));
            }
        } catch (Exception e) {
            // If API fails, return a route with calculated straight-line distance
            System.err.println("Routing API error: " + e.getMessage());
        }
        
        // Fallback: calculate straight-line distance
        Route route = new Route();
        route.setUserId(request.getUserId());
        route.setStartStationId(request.getStartStationId());
        route.setEndStationId(request.getEndStationId());
        route.setStartLat(request.getStartLat());
        route.setStartLng(request.getStartLng());
        route.setEndLat(request.getEndLat());
        route.setEndLng(request.getEndLng());
        
        // Haversine formula for approximate distance
        double earthRadius = 6371000; // meters
        double dLat = Math.toRadians(request.getEndLat() - request.getStartLat());
        double dLng = Math.toRadians(request.getEndLng() - request.getStartLng());
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(Math.toRadians(request.getStartLat())) * Math.cos(Math.toRadians(request.getEndLat())) *
                   Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        route.setDistanceMeters(earthRadius * c);
        route.setDurationSeconds(route.getDistanceMeters() / 10); // Assume 10 m/s average speed
        
        return new RouteResponseDTO(repo.save(route));
    }

    // DELETE - Remove route
    @Transactional
    public void deleteRoute(Long id) {
        repo.deleteById(id);
    }
    
    // Helper classes for OSRM response
    public static class OsrmResponse {
        private List<OsrmRoute> routes;

        public List<OsrmRoute> getRoutes() { return routes; }
        public void setRoutes(List<OsrmRoute> routes) { this.routes = routes; }
    }
    
    public static class OsrmRoute {
        private String geometry;
        private Double distance;
        private Double duration;

        public String getGeometry() { return geometry; }
        public void setGeometry(String geometry) { this.geometry = geometry; }
        public Double getDistance() { return distance; }
        public void setDistance(Double distance) { this.distance = distance; }
        public Double getDuration() { return duration; }
        public void setDuration(Double duration) { this.duration = duration; }
    }
}
