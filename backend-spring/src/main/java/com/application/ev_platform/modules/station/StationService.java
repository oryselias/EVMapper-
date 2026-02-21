package com.application.ev_platform.modules.station;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    private final StationRepository repo;

    public StationService(StationRepository repo) {
        this.repo = repo;
    }

    // GET all stations
    @Transactional(readOnly = true)
    public List<StationResponseDTO> getAllStations() {
        return repo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    // GET station by ID
    @Transactional(readOnly = true)
    public Optional<StationResponseDTO> getStationById(Long id) {
        return repo.findById(id).map(this::toResponse);
    }

    // GET nearby stations
    @Transactional(readOnly = true)
    public List<StationResponseDTO> getNearbyStations(double lat, double lng, double radiusKm) {
        // Convert km to meters
        double radiusMeters = radiusKm * 1000;
        return repo.findNearbyStations(lat, lng, radiusMeters).stream()
                .map(this::toResponse)
                .toList();
    }

    // GET only active stations
    @Transactional(readOnly = true)
    public List<StationResponseDTO> getActiveStations() {
        return repo.findByActiveTrue().stream()
                .map(this::toResponse)
                .toList();
    }

    // POST - create new station
    @Transactional
    public StationResponseDTO saveStation(Station station) {
        Station saved = repo.save(station);
        return toResponse(saved);
    }

    // PUT - update station
    @Transactional
    public StationResponseDTO updateStation(Long id, StationRequestDTO request) {
        Station station = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found with id: " + id));
        
        station.setName(request.getName());
        station.setAddress(request.getAddress());
        
        if (request.getLat() != 0 && request.getLng() != 0) {
            org.locationtech.jts.geom.GeometryFactory gf = 
                new org.locationtech.jts.geom.GeometryFactory(
                    new org.locationtech.jts.geom.PrecisionModel(), 4326);
            station.setLocation(gf.createPoint(
                new org.locationtech.jts.geom.Coordinate(request.getLng(), request.getLat())
            ));
        }
        
        return toResponse(repo.save(station));
    }

    // DELETE - soft delete (deactivate)
    @Transactional
    public void deactivateStation(Long id) {
        Station station = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found with id: " + id));
        station.setActive(false);
        repo.save(station);
    }

    // DELETE - hard delete
    @Transactional
    public void deleteStation(Long id) {
        repo.deleteById(id);
    }

    private StationResponseDTO toResponse(Station station) {
        return new StationResponseDTO(
                station.getId(),
                station.getName(),
                station.getAddress(),
                station.getLocation().getY(), // lat
                station.getLocation().getX(), // lng
                station.getActive()
        );
    }
}
