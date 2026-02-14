package com.application.ev_platform.modules.station;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StationService {

    private final StationRepository repo;

    public StationService(StationRepository repo) {
        this.repo = repo;
    }

    public List<StationResponseDTO> getAllStations() {
        return repo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public StationResponseDTO saveStation(Station station) {
        Station saved = repo.save(station);
        return toResponse(saved);
    }

    private StationResponseDTO toResponse(Station station) {
        return new StationResponseDTO(
                station.getId(),
                station.getName(),
                station.getAddress(),
                station.getLocation().getY(), // lat
                station.getLocation().getX()  // lng
        );
    }
}
