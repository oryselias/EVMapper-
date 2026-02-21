package com.application.ev_platform.modules.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    // Find stations within a radius (in meters)
    @Query(value = 
        "SELECT * FROM stations " +
        "WHERE ST_DWithin(location, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326), :radius) " +
        "AND active = true",
        nativeQuery = true)
    List<Station> findNearbyStations(@Param("lat") double lat, @Param("lng") double lng, @Param("radius") double radius);
    
    // Find active stations
    List<Station> findByActiveTrue();
}
