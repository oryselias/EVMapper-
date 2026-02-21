package com.application.ev_platform.modules.station;

import org.locationtech.jts.geom.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationService service;
    private final GeometryFactory geometryFactory =
            new GeometryFactory(new PrecisionModel(), 4326);

    public StationController(StationService service) {
        this.service = service;
    }

    // GET /api/stations - Get all stations
    @GetMapping
    public List<StationResponseDTO> getAllStations() {
        return service.getAllStations();
    }

    // GET /api/stations/active - Get only active stations
    @GetMapping("/active")
    public List<StationResponseDTO> getActiveStations() {
        return service.getActiveStations();
    }

    // GET /api/stations/{id} - Get station by ID
    @GetMapping("/{id}")
    public StationResponseDTO getStationById(@PathVariable Long id) {
        return service.getStationById(id)
                .orElseThrow(() -> new RuntimeException("Station not found"));
    }

    // GET /api/stations/nearby?lat=X&lng=Y&radius=Z - Get nearby stations
    @GetMapping("/nearby")
    public List<StationResponseDTO> getNearbyStations(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "5.0") double radius) {
        return service.getNearbyStations(lat, lng, radius);
    }

    // POST /api/stations - Create new station
    @PostMapping
    public StationResponseDTO createStation(@RequestBody StationRequestDTO request) {
        Point location = geometryFactory.createPoint(
                new Coordinate(request.getLng(), request.getLat())
        );

        Station station = new Station();
        station.setName(request.getName());
        station.setAddress(request.getAddress());
        station.setLocation(location);
        station.setActive(true);

        return service.saveStation(station);
    }

    // PUT /api/stations/{id} - Update station
    @PutMapping("/{id}")
    public StationResponseDTO updateStation(@PathVariable Long id, @RequestBody StationRequestDTO request) {
        return service.updateStation(id, request);
    }

    // DELETE /api/stations/{id} - Soft delete (deactivate)
    @DeleteMapping("/{id}")
    public void deactivateStation(@PathVariable Long id) {
        service.deactivateStation(id);
    }
}
