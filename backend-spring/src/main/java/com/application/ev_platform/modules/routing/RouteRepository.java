package com.application.ev_platform.modules.routing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByUserId(Long userId);
    List<Route> findByStartStationIdOrEndStationId(Long startStationId, Long endStationId);
}
