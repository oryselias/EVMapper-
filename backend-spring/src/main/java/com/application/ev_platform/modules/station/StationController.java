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

    @GetMapping
    public List<StationResponseDTO> getAllStations() {
        return service.getAllStations();
    }

    @PostMapping
    public StationResponseDTO createStation(@RequestBody StationRequestDTO request) {

        Point location = geometryFactory.createPoint(
                new Coordinate(request.getLng(), request.getLat())
        );

        Station station = new Station();
        station.setName(request.getName());
        station.setAddress(request.getAddress());
        station.setLocation(location);

        return service.saveStation(station);
    }

}